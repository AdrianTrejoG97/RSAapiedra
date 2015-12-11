
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import static javafx.scene.input.KeyCode.M;
import static javafx.scene.input.KeyCode.N;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alumno
 */
public class rsa extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @SuppressWarnings("empty-statement")
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String p= request.getParameter("p1");
        String q= request.getParameter("q1");
        String msg = request.getParameter("msg");
        byte[] msgb = msg.getBytes();
        ByteBuffer buffer = ByteBuffer.wrap(msgb);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        int msgr = buffer.getShort();
        int p1 = Integer.parseInt(p);
        int q1 = Integer.parseInt(q);
        int n=0;
        n = p1 * q1;
        int fi=0;
        fi= (p1-1)*(q1-1);
        int r = (1-fi+1)+fi;
        int e=1;
        int div = 2;
       for(int con=1;con==fi;con++)
	{
            e=con;
              if((e%div==1)&&(fi%div==1)) {
                  con=fi;
              } 
              else{}
	}
        int d = 1%fi/e;
        int cipher = (msgr^e)%n;
        int mcipher= (cipher^d)%n;
        String mciphers = ""+mcipher;
        int len = mciphers.length();
        byte[] descipher;
        //ByteBuffer buf = ByteBuffer.allocate(len);
        //buf.order(ByteOrder.BIG_ENDIAN);
        //buf.putInt(mcipher);
        //buf.flip();
        
        //descipher = new byte[len];
        //for(int c=0; c<len ;c++){
         //   descipher[c] = buf.array()[c];
        //}
       
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Encriptacion RSA</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>La P es: " + p +"</h1>");
            out.println("<h1>La Q es: " + q +"</h1>");
            out.println("<h1>La N es: " + n +"</h1>");
            out.println("<h1>La E es: " + e +"</h1>");
            out.println("<h1>Phi es: " + fi +"</h1>");
            out.println("<h1>La D es:" + d +"</h1>");
            out.println("<h1>Mensaje cifrado es:" + cipher +"</h1>");
            out.println("<h1>Mensaje descifrado es:" + mcipher +"</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
