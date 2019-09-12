<%-- 
    Document   : style
    Created on : Apr 13, 2019, 10:38:32 PM
    Author     : Sifat Ahmed
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    int userid = -1;
    String username = "";
    if (session.isNew()) {
        session.setAttribute("userid", -1);
        session.setAttribute("username", "");
    } else {
        userid = Integer.parseInt(session.getAttribute("userid").toString());
        username = session.getAttribute("username").toString();
    }


%>

<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Styler</title>

        <!-- Bootstrap core CSS -->
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="css/business-frontpage.css" rel="stylesheet">


    </head>

    <body>

        <!-- Navigation -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
            <div class="container">
                <a class="navbar-brand" href="index.jsp">Styler</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item active">
                            <a class="nav-link" href="index.jsp">Home</a>
                        </li>

                        <% if (userid == -1) {
                        %>
                        <li class="nav-item">
                            <a class="nav-link" href="getin.jsp">Login</a>
                        </li>
                        <%
                        } else {
                        %>
                        <form method="post" enctype="form-data" action="UserImage">
                            <li class="nav-item">
                                <button class="btn-dark" type="submit" value="UserImage" ><%= username%></button>
                            </li>
                        </form>
                        <li class="nav-item">
                            <a class="nav-link" href="logout.jsp">Logout</a>
                        </li>
                        <%
                            }
                        %>




                    </ul>
                </div>
            </div>
        </nav>

        <!-- Header -->
        <header class="bg-primary py-5 mb-5">
            <div class="container h-100">
                <div class="row h-100 align-items-center">
                    <div class="col-lg-12">
                        <center>
                            <h1 class="display-4 text-white mt-5 mb-2">Styler! style your images</h1>
                            <p class="lead mb-5 text-white-50">
                                Your images are below!

                            </p>
                        </center>
                    </div>
                </div>
            </div>
        </header>

        <!-- Page Content -->
        <div class="container">


            <!-- /.row -->

            <div class="row">
                <% ArrayList<String> list = (ArrayList<String>) request.getAttribute("image_src");
                    ArrayList<String> name = (ArrayList<String>) request.getAttribute("image_name");
                    int i = 0;
                    for (String source : list) {
                %>
                <div class="col-md-4 mb-5">
                    <div class="card h-100">

                        <a onclick = "get_image('<%= source%>', '<%= name.get(i)%>')">
                            <img class="card-img-top" src=' <%= source%>' >
                        </a>


                        <div class="card-body">
                            <h4 class="card-title"><center><%= name.get(i)%></center>  </h4>
                            <p class="" >
                            <% if(userid == -1) { %>
                            <center>Login to Save Image</center>
                            <% } %>
                            </p>
                        </div>
                    </div>
                </div>
                <%  i++;
                    }%>


            </div>
            <!-- /.row -->

        </div>

        <footer class="py-5 bg-dark">
            <div class="container">
                <p class="m-0 text-center text-white">One Last Project</p>
            </div>
            <!-- /.container -->
        </footer>

        <!-- Bootstrap core JavaScript -->


        <script>

            function get_image(data, name) {
                // convert base64 to raw binary data held in a string
                // doesn't handle URLEncoded DataURIs - see SO answer #6850276 for code that does this
                var byteString = atob(data.split(',')[1]);

                // separate out the mime component
                var mimeString = data.split(',')[0].split(':')[1].split(';')[0]

                // write the bytes of the string to an ArrayBuffer
                var ab = new ArrayBuffer(byteString.length);

                // create a view into the buffer
                var ia = new Uint8Array(ab);

                // set the bytes of the buffer to the correct values
                for (var i = 0; i < byteString.length; i++) {
                    ia[i] = byteString.charCodeAt(i);
                }

                // write the ArrayBuffer to a blob, and you're done
                var blob = new Blob([ab], {type: mimeString});
                saveAs(blob, name + ".png");
            }

        </script>



        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="vendor/FileSaver.js"></script>
    </body>

</html>