package com.study.common.util;

import com.study.login.vo.UserVO;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// Member 게시판은 일반 사용자가 볼 수 없어야되겠죠??
// 관리자만 볼 수 있어야 합니다.
public class MangerCheckInterceptor extends HandlerInterceptorAdapter {

    // Controller 가기전에
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        UserVO user = (UserVO) session.getAttribute("USER_INFO");
        if (user==null) { // 로그인이 안된경우
            response.sendRedirect("/login/login.wow");
            return false;
        } else { // 로그인은 했어.
            if (!user.getUserRole().equals("MANAGER")) { // user인 경우
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "당신은 권한 부족");
                return false;
            }
        }
        return super.preHandle(request, response, handler);
    }
}
