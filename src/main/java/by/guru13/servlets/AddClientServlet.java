package by.guru13.servlets;

import by.guru13.models.clinic.Address;
import by.guru13.models.clinic.Client;
import by.guru13.store.ClientCashe;
import by.guru13.models.clinic.animals.Cat;
import by.guru13.models.clinic.animals.Dog;
import by.guru13.models.clinic.animals.Parrot;
import by.guru13.models.clinic.animals.Pet;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ASUS on 12.08.2015.
 */
@WebServlet(name = "AddClientServlet", urlPatterns = "/add")
public class AddClientServlet extends HttpServlet {

        public ClientCashe clinic ;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        clinic = new ClientCashe();
        Pet pet = null;
        Address address = null;
        address = new Address(request.getParameter("clientCity"), request.getParameter("clientStreet"),
                Integer.valueOf(request.getParameter("clientHouse")), Integer.valueOf(request.getParameter("clientApartment")));

        String male = "male";
        if (request.getParameter("sexPet").equals("female")) {
            male = "female";
        }
        if (request.getParameter("pets").equals("dog")) {
            pet = new Dog(request.getParameter("petName"), "dog", male, Integer.valueOf(request.getParameter("petAge")));
        } else if (request.getParameter("pets").equals("cat")) {
            pet = new Cat(request.getParameter("petName"), "cat", male, Integer.valueOf(request.getParameter("petAge")));
        } else if (request.getParameter("pets").equals("parrot")) {
            pet = new Parrot(request.getParameter("petName"), "parrot", male, Integer.valueOf(request.getParameter("petAge")));
        }
        String maleClient = "male";
        if (request.getParameter("sex").equals("female")) {
            maleClient = "female";
        }

        if (!request.getParameter("clientName").isEmpty() && !request.getParameter("clientAge").isEmpty()) {
//            this.clinic.add(new Client(request.getParameter("clientName"), pet, maleClient,
//                    Integer.valueOf(request.getParameter("clientAge")), address));
            clinic.add(new Client(request.getParameter("clientName"), maleClient, Integer.valueOf(request.getParameter("clientAge"))), address, pet);
            clinic.close();
        }

        request.setAttribute("clinic", clinic);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/clinic/ClinicView.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void destroy() {
        super.destroy();
        clinic.close();
    }
}
