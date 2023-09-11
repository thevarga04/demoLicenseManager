package demo.licensemanager.realm;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class CorrelationIdInterceptor implements HandlerInterceptor {


  @Override
  public boolean preHandle(
    @NonNull HttpServletRequest request
    , @NonNull HttpServletResponse response
    , @NonNull Object handler
  ) throws Exception {
    response.setHeader("CorrelationId", UUIDFactory.newUuid());

    return HandlerInterceptor.super.preHandle(request, response, handler);
  }


//  @Override
//  public void postHandle(
//    @NonNull HttpServletRequest request
//    , @NonNull HttpServletResponse response
//    , @NonNull Object handler
//    , ModelAndView modelAndView
//  ) throws Exception {
//    response.setHeader("CorrelationId", UUIDFactory.newUuid());
//
//    HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
//  }


}
