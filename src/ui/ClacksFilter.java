package ui;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;


public class ClacksFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException {
        ((HttpServletResponse)response).addHeader("X-Clacks-Overhead", "GNU Terry Pratchett");
    }
    public void destroy() {}
    public void init(FilterConfig filterConfig) {}
}
