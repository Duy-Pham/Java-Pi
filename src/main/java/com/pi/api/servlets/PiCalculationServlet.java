package com.pi.api.servlets;

import com.pi.applicationcore.business.PiBusiness;
import com.pi.applicationcore.dto.PiRequest;
import com.pi.applicationcore.dto.PiResponseResult;
import com.pi.applicationcore.interfaces.PiBusinessLocal;

import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;

@WebServlet("/PiCalculation")
@Stateless
public class PiCalculationServlet extends HttpServlet {

    @EJB//(lookup = "java:global:/MYSTUFF")
    private PiBusinessLocal _piBusinessLocal;

//    public PiCalculationServlet(PiBusiness piBusiness){
//        _piBusiness = piBusiness;
//    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Context ctx = null;
        try {
            ctx = new InitialContext();
            _piBusinessLocal =
                    (PiBusiness) ctx.lookup("java:comp/env/ejb/piBusiness");
        } catch (NamingException e) {
            e.printStackTrace();
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h3>Hello World!</h3>");

        PiRequest piRequest = new PiRequest();
//        piRequest.setRawNumber(number);
        piRequest.setRawNumber("9876543");
        PiResponseResult piResponseResult = _piBusinessLocal.exec(piRequest);

        out.println(piResponseResult.getValue());
    }

}
