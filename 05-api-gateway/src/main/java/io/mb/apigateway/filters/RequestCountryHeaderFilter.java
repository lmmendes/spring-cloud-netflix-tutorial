package io.mb.apigateway.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.context.annotation.FilterType;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

public class RequestCountryHeaderFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        String country = getCountryFromRequestContext(ctx.getRequest());
        if( country != null && !country.isEmpty() ) {
            ctx.addZuulRequestHeader("X-Country", country);
        }
        return null;
    }

    private String getRequestIp(@NotNull HttpServletRequest request) {

        // X-Forward could take a list of ips be let's assume that we never pass more than one proxy
        String ip = request.getHeader("X-FORWARDED-FOR");
        if (ip == null || ip.isEmpty() ) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }

    // A service IP API could be use to map the IP to Country (never use is for production nor critical applications)
    // http://ip-api.com/docs/api:json#test
    private String getCountryFromRequestContext(HttpServletRequest request){
        String ip = getRequestIp(request);
        if( ip == null || ip.isEmpty() ){
            return "Unknown";
        }
        return "Portugal";
    }
}
