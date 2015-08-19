package by.guru13.servlets;

import by.guru13.store.ClientCashe;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ASUS on 13.08.2015.
 */
public class DeleteClientServletTest extends Mockito {
    private ClientCashe clinic = ClientCashe.getInstance();

    @Test
    public void testDoPost() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("")).thenReturn("");
        when(request.getParameter("")).thenReturn("");

    }

    @Test
    public void testDoGet() throws Exception {

    }
}