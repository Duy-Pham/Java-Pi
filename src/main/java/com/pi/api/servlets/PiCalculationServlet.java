package com.pi.api.servlets;

import com.pi.applicationcore.dto.PiRequest;
import com.pi.applicationcore.dto.PiResponseResult;
import com.pi.applicationcore.interfaces.PiBusiness;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;

@WebServlet("/PiCalculation")
public class PiCalculationServlet extends HttpServlet {

    @EJB
    private PiBusiness _piBusiness;

    public PiCalculationServlet(PiBusiness piBusiness){
        _piBusiness = piBusiness;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h3>Hello World!</h3>");

        PiRequest piRequest = new PiRequest();
//        piRequest.setRawNumber(number);
        piRequest.setRawNumber("9876543");
        PiResponseResult piResponseResult = _piBusiness.exec(piRequest);

        out.println(piResponseResult.getValue());
    }

}
