<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <link rel="stylesheet" src="style.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
        <title>Sistema De Ventas</title>
        <style>
      body {
        background-image: url("https://wallpapers.com/images/hd/world-background-jf1qha7jydgwd1l3.jpg");
      }
      h1,
      h3 {
        color: blue;
      }

      body {
        text-align: center;
      }
    </style>
    </head>
    <body >
     

        <div class="container mt-4 col-lg-4" >
            <div class="card col-sm-8" style="background-color: darkturquoise" >
                <div class="card-body" style="background-color: darkturquoise">
                    <form class="form-sign" action="validar" method="POST">
                        <div class="form-group text-center">
                            <h3>Login</h3>
                            <img src="https://cdn-icons-png.flaticon.com/512/5087/5087579.png" height="70" width="170"/>
                            <label>Bienvenido al sistema</label>
                         </div>
                        <div class="form-group" >
                            <label>Usuario:</label>
                            <input type="text" name="txtuser" class="form-control">
                        </div>
                        <div class="form-group" >
                            <label>Contaseña:</label>
                            <input type="password" name="txtpass" class="form-control">
                        </div>
                        <input type="submit" name="accion" value="Ingresar" class="btn btn-primary btn-b">
                    </form>
                </div>
            </div>
        </div>
       <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </body>
</html>

