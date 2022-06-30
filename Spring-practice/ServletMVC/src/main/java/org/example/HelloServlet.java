package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        System.out.println("init");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ApplicationContext context = (ApplicationContext) getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE); //스프링 컨테이너에 서블릿 컨텍스트 연결 및 가져오기
        HelloService helloService = context.getBean(HelloService.class); //스프링컨테이너에서 등록된 service의 bean을 가져온다.
        String name = helloService.getName();



        //req와 resp는 filter에서 등록된 ServletRequest와 ServletResponse와 연결되는 것이다.
        System.out.println("doGet");
        resp.getWriter().println("<html>");
        resp.getWriter().println("<head>");
        resp.getWriter().println("<body>");
        resp.getWriter().println("<h1>hello " + getServletContext().getAttribute("name") + "servlet</h1>");
        resp.getWriter().println("</body>");
        resp.getWriter().println("</head>");
        resp.getWriter().println("</html>");
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }
}
