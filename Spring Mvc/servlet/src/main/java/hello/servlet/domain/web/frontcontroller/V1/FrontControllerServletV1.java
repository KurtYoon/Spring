package hello.servlet.domain.web.frontcontroller.V1;

import hello.servlet.domain.web.frontcontroller.V1.controller.MemberFormControllerV1;
import hello.servlet.domain.web.frontcontroller.V1.controller.MemberListControllerV1;
import hello.servlet.domain.web.frontcontroller.V1.controller.MemberSaveControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {
    // v1 하위 경로는 모드 해당 메서드 호출

    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    public FrontControllerServletV1() {
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");

        // /front-controller/v1/members
        String requestURI = request.getRequestURI();

        // 다형성에 의해 인터페이스를 받음
        // /front-controller/v1/members이 요청될 때 Controllerv1 comtroller = MemberListControllerV1()외 같음
        ControllerV1 controller = controllerMap.get(requestURI); // 컨트롤러가 찾아짐
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        // override된 메서드가 호출됨
        controller.process(request, response);
    }
}
