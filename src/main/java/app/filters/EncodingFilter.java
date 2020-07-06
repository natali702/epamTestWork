package app.filters;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req,
                         ServletResponse resp,
                         FilterChain chain) throws ServletException, IOException {
        System.out.println("start doFilter from EncodingFilter");
        resp.setCharacterEncoding("UTF-8");
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
    }
}
