package com.example.demo.web.session;

import com.example.demo.domain.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.assertj.core.api.Assertions.assertThat;

class SessionManagerTest {
    SessionManager sessionManager = new SessionManager();

    @Test
    void sessionTest(){

        MockHttpServletResponse response = new MockHttpServletResponse();

        //make a session
        Member member = new Member();
        sessionManager.createSession(member, response);

        //save response Cookie
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(response.getCookies()); //mySessionId = 123124-123123-sdf

        Object result = sessionManager.getSession(request);
        assertThat(result).isEqualTo(member);

        sessionManager.expire(request);
        Object expiredSession = sessionManager.getSession(request);
        assertThat(expiredSession).isNull();


    }

}