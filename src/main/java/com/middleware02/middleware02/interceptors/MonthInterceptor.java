package com.middleware02.middleware02.interceptors;

import com.middleware02.middleware02.entities.Month;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MonthInterceptor implements HandlerInterceptor {

    List<Month> monthList = List.of(
            new Month(1,"January","Gennaio","Januar"),
        new Month(2,"February","Febbraio","Februar"),
        new Month(3,"March","Marzo","Marsch"),
        new Month(4,"April","Aprile","April"),
            new Month(5,"May","Maggio","Kann"),
        new Month(6,"June","Giugno","Juni"));


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getRequestURI().contains("/months")) {
            String monthNumber = request.getHeader("monthNumber");
            if (monthNumber != null) {
                int intNumber = Integer.parseInt(monthNumber);
                Optional<Month> monthFound = monthList.stream()
                        .filter(singleMonth -> singleMonth.getMonthNumber() == intNumber)
                        .findFirst();
                if (monthFound.isPresent()) {
                    request.setAttribute("MonthInterceptor-month", monthFound.get());
                } else {
                    request.setAttribute("MonthInterceptor-month",
                            new Month(0, "nope", "nope", "nope"));
                }
                response.setStatus(200);
                return true;
            } else {
                response.setStatus(400);
                return false;
            }
        }
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
