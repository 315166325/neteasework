package com.netease.bigwork.controller;

import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Locale;
import java.util.Map;

public class FreeMarkerViewUtil extends FreeMarkerView{
    @Override
         protected void doRender(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)
                 throws Exception {
                 exposeModelAsRequestAttributes(model, request);
                 SimpleHash fmModel = buildTemplateModel(model, request, response);

                if (logger.isDebugEnabled()) {
                         logger.debug("Rendering FreeMarker 模版 [" + getUrl() + "] in FreeMarkerView '" + getBeanName() + "'");
                     }
        // Grab the locale-specific version of the template.
                 Locale locale = RequestContextUtils.getLocale(request);

                 if (Boolean.FALSE.equals(model.get("CREATE_HTML"))) {
                         processTemplate(getTemplate(locale), fmModel, response);
                     } else {
                         createHTML(getTemplate(locale), fmModel, request, response);
                     }
             }

                 public void createHTML(Template template, SimpleHash model, HttpServletRequest request,
                                        HttpServletResponse response) throws IOException, TemplateException, ServletException {
                 // 站点根目录的绝对路径
                 String basePath = request.getSession().getServletContext().getRealPath("/")+"WEB-INF\\";
                 String requestHTML = this.getRequestHTML(request);
                     System.out.println("转发请求:"+requestHTML);
                 // 静态页面绝对路径
                 String htmlPath = basePath + requestHTML;
                 System.out.println("静态页面绝对路径===========>>:"+htmlPath);
                 File htmlFile = new File(htmlPath);
                 if (!htmlFile.getParentFile().exists()) {
                         htmlFile.getParentFile().mkdirs();
                     }
                 if (!htmlFile.exists()) {
                         htmlFile.createNewFile();
                     }
                 Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(htmlFile), "UTF-8"));
                 // 处理模版
                 template.process(model, out);
                 out.flush();
                 out.close();
                     System.out.println("请求路径"+"/WEB-INF/"+requestHTML);
                 /* 将请求转发到生成的htm文件 */
                request.getRequestDispatcher("/WEB-INF/"+requestHTML).forward(request, response);
             }

                 private String getRequestHTML(HttpServletRequest request) {
                 // web应用名称,部署在ROOT目录时为空
                 String contextPath = request.getContextPath();
                 // web应用/目录/文件.do
                 String requestURI = request.getRequestURI();
                     System.out.println("请求的路径是"+requestURI);
                 // basePath里面已经有了web应用名称，所以直接把它replace掉，以免重复
                 requestURI = requestURI.replaceFirst(contextPath, "");
                     System.out.println("路径是"+requestURI);
                 // 将.do改为.html,稍后将请求转发到此html文件
                 requestURI = requestURI.substring(1,requestURI.length()-1) + ".html";
//                     requestURI +=  ".html";
                 return requestURI;
             }
}
