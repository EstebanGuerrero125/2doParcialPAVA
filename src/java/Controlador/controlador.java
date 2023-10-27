package Controlador;


import Config.GenerarSerie;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import Modelo.*;
import jakarta.servlet.http.HttpSession;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class controlador extends HttpServlet {

    Empleado em = new Empleado();
    EmpleadoDAO edao = new EmpleadoDAO();
    Cliente c = new Cliente();
    ClienteDAO cdao = new ClienteDAO();
    Producto p= new Producto();
    ProductoDAO pdao= new ProductoDAO();
    int ide;
    int idc;
    int idp;
    
    Venta v= new Venta();
    List<Venta> lista= new ArrayList<>();
    int item;
    int cod;
    String descripcion;
    double precio;
    int cant;
    double subtotal;
    double totalPagar;
    
    String numeroSerie;
    VentaDAO vdao=new VentaDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");
        HttpSession sesion = request.getSession();
        Empleado us = (Empleado) sesion.getAttribute("usuario");
        
        if(us!= null){
        
       

        if (menu.equals("Principal")) {
            request.getRequestDispatcher("principal.jsp").forward(request, response);
        }
        if (menu.equals("Empleado")) {

            switch (accion) {
                case "Listar":
                    List listae = edao.listar();
                    request.setAttribute("empleados", listae);

                    break;
                case "Agregar":
                    String dni = request.getParameter("txtDni");
                    String nom = request.getParameter("txtNombres");
                    String tel = request.getParameter("txtTel");
                    String est = request.getParameter("txtEstado");
                    String user = request.getParameter("txtUser");
                    String contra = asegurarClave(request.getParameter("txtContrasena"));
                    em.setDni(dni);
                    em.setNom(nom);
                    em.setTel(tel);
                    em.setEstado(est);
                    em.setUser(user);
                    em.setContrasena(contra);
                    edao.agregar(em);
                    request.getRequestDispatcher("controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;
                case "Editar":
                    ide = Integer.parseInt(request.getParameter("id"));
                    Empleado e = edao.listarId(ide);
                    request.setAttribute("empleado", e);
                    request.getRequestDispatcher("controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":
                    String dni1 = request.getParameter("txtDni");
                    String nom1 = request.getParameter("txtNombres");
                    String tel1 = request.getParameter("txtTel");
                    String est1 = request.getParameter("txtEstado");
                    String user1 = request.getParameter("txtUser");
                    em.setDni(dni1);
                    em.setNom(nom1);
                    em.setTel(tel1);
                    em.setEstado(est1);
                    em.setUser(user1);
                    em.setId(ide);
                    edao.actualizar(em);
                    request.getRequestDispatcher("controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;
                case "Delete":
                    ide = Integer.parseInt(request.getParameter("id"));
                    edao.delete(ide);
                    request.getRequestDispatcher("controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;
                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("Empleado.jsp").forward(request, response);
        }
        if (menu.equals("Clientes")) {
            switch (accion) {
                 case "Listar":
                    List listac = cdao.listar();
                    request.setAttribute("clientes", listac);

                    break;
                case "Agregar":
                    String dni = request.getParameter("txtDni");
                    String nom = request.getParameter("txtNombres");
                    String dir = request.getParameter("txtDir");
                    String est = request.getParameter("txtEstado");
                    c.setDni(dni);
                    c.setNom(nom);
                    c.setDir(dir);
                    c.setEstado(est);
                    cdao.agregar(c);
                    request.getRequestDispatcher("controlador?menu=Clientes&accion=Listar").forward(request, response);
                    break;
                case "Editar":
                    idc = Integer.parseInt(request.getParameter("id"));
                    Cliente cl = cdao.listarId(idc);
                    request.setAttribute("cliente", cl);
                    request.getRequestDispatcher("controlador?menu=Clientes&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":
                    String dni1 = request.getParameter("txtDni");
                    String nom1 = request.getParameter("txtNombres");
                    String dir1 = request.getParameter("txtDir");
                    String est1 = request.getParameter("txtEstado");
                    c.setDni(dni1);
                    c.setNom(nom1);
                    c.setDir(dir1);
                    c.setEstado(est1);
                    c.setId(idc);
                    System.out.println("EL ID A EDITAR ES:"+idc);
                    cdao.actualizar(c);
                    request.getRequestDispatcher("controlador?menu=Clientes&accion=Listar").forward(request, response);
                    break;
                case "Delete":
                    idc = Integer.parseInt(request.getParameter("id"));
                    cdao.delete(idc);
                    request.getRequestDispatcher("controlador?menu=Clientes&accion=Listar").forward(request, response);
                    break;
                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("Clientes.jsp").forward(request, response);
        }
        if (menu.equals("Producto")) {
            switch (accion) {
                 case "Listar":
                    List listap = pdao.listar();
                    request.setAttribute("productos", listap);
                     System.out.println("Test GIT");
                    break;
                case "Agregar":
                    String nom = request.getParameter("txtNombres");
                    Double pre = Double.parseDouble(request.getParameter("txtPrecio"));
                    int sto = Integer.parseInt(request.getParameter("txtStock"));
                    String est = request.getParameter("txtEstado");
                    p.setNom(nom);
                    p.setPrecio(pre);
                    p.setStock(sto);
                    p.setEstado(est);
                    pdao.agregar(p);
                    request.getRequestDispatcher("controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;
                case "Editar":
                    idp = Integer.parseInt(request.getParameter("id"));
                    Producto pr = pdao.listarId(idp);
                    request.setAttribute("producto", pr);
                    request.getRequestDispatcher("controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":
                    String nomp = request.getParameter("txtNombres");
                    Double prep = Double.parseDouble(request.getParameter("txtPrecio"));
                    int stop = Integer.parseInt(request.getParameter("txtStock"));
                    String estp = request.getParameter("txtEstado");
                    p.setNom(nomp);
                    p.setPrecio(prep);
                    p.setStock(stop);
                    p.setEstado(estp);
                    p.setId(idp);
                    System.out.println("EL ID A EDITAR ES:" + idp);
                    pdao.actualizar(p);
                    request.getRequestDispatcher("controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;
                case "Delete":
                    idp = Integer.parseInt(request.getParameter("id"));
                    pdao.delete(idp);
                    request.getRequestDispatcher("controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;
                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("Producto.jsp").forward(request, response);
        }
        if (menu.equals("NuevaVenta")) {
            switch (accion) {
                case "BuscarCliente":
                    String dni = request.getParameter("codigocliente");
                    c.setDni(dni);
                    c= cdao.buscar(dni);
                    request.setAttribute("c", c);
                    request.setAttribute("nserie",numeroSerie);
                    break;
                case "BuscarProducto":
                    int id=Integer.parseInt(request.getParameter("codigoproducto"));
                    p=pdao.listarId(id);
                    request.setAttribute("c", c);
                    request.setAttribute("producto", p);
                    request.setAttribute("lista", lista);
                    request.setAttribute("totalPagar", totalPagar);
                    request.setAttribute("nserie",numeroSerie);
                    break;
                 case "Agregar":
                     request.setAttribute("c", c);
                     totalPagar=0.0;
                   item=item+1;
                   cod=p.getId();
                   descripcion=request.getParameter("nombreproducto");
                   precio=Double.parseDouble(request.getParameter("precio"));
                   cant=Integer.parseInt(request.getParameter("cant"));
                   subtotal=precio*cant;
                   v=new Venta();
                   v.setItem(item);
                   v.setIdproducto(cod);
                   v.setDescripcionP(descripcion);
                   v.setPrecio(precio);
                   v.setCantidad(cant);
                   v.setSubtotal(subtotal);
                   lista.add(v);
                     for (int i = 0; i < lista.size(); i++) {
                         totalPagar=totalPagar+lista.get(i).getSubtotal();
                     }
                   request.setAttribute("totalPagar", totalPagar);
                   request.setAttribute("lista", lista);
                   request.setAttribute("nserie",numeroSerie);
                    break;
                 case "GenerarVenta":
                     //Actualizar del stock
                     for (int i=0; i<lista.size();i++){
                         Producto p=new Producto();
                         int cantidad=lista.get(i).getCantidad();
                         int idproducto=lista.get(i).getIdproducto();
                         ProductoDAO aO =new ProductoDAO();
                         p =aO.buscar(idproducto);
                         int sac=p.getStock()-cantidad;
                         aO.actualizarstock(idproducto, sac);
                         
                     
                     }
                     
                     //Guardar Venta
                    v.setIdcliente(c.getId());
                    v.setIdempleado(us.getId());//Se usa us debido a que se inicio sesion con un empleado
                    v.setNumserie(numeroSerie);
                    v.setFecha("2023-10-26");
                    v.setMonto(totalPagar);
                    v.setEstado("1");
                    vdao.guardarVentas(v);
                    int idv=Integer.parseInt(vdao.IdVentas());
                     for (int i = 0; i < lista.size(); i++) {
                         v=new Venta();
                         v.setId(idv);
                         v.setIdcliente(lista.get(i).getIdcliente());
                         v.setIdproducto(lista.get(i).getIdproducto());
                         v.setCantidad(lista.get(i).getCantidad());
                         v.setPrecio(lista.get(i).getPrecio());
                         vdao.guardarDetalleventas(v);  
                     }
                    break;
                default:
                    numeroSerie=vdao.GenerarSerie();
                    if(numeroSerie==null){
                        numeroSerie="00000001";
                        request.setAttribute("nserie",numeroSerie);
                    }else{
                        int incremetar=Integer.parseInt(numeroSerie);
                        GenerarSerie gs= new GenerarSerie();
                        numeroSerie=gs.NumeroSerie(incremetar);
                        request.setAttribute("nserie",numeroSerie);
                    }
                    request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);
                    //throw new AssertionError();
            }
            request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);
        }
        
        
         }else{
            request.getRequestDispatcher("controlador?menu=Principal").forward(request, response);
        }
    }
    
    private String asegurarClave(String txtClaro){
       String claveSha=null;
        try { 
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            sha256.update(txtClaro.getBytes() );
            claveSha = Base64.getEncoder().encodeToString(sha256.digest());
            System.out.println("Clave sha es: " +claveSha);
            System.out.println("Longitud: "+ claveSha.length());
            
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("ERROR AL INSTANCIAR sha256 "+ex.getMessage() );
        
        }
        
    return claveSha;
    
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
