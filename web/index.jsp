<%-- 
    Document   : style
    Created on : Apr 13, 2019, 10:38:32 PM
    Author     : Sifat Ahmed
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<% 
    int userid = -1;
    String username="";
    if(session.isNew())
    {
        session.setAttribute("userid", -1);
        session.setAttribute("username", "");
    }
    else
    {
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
                        
                        <% if (userid == -1) 
                        {
                        %>
                            <li class="nav-item">
                                <a class="nav-link" href="getin.jsp">Login</a>
                            </li>
                        <%
                        } else {
                        %>
                        <form method="post" enctype="form-data" action="UserImage">
                            <li class="nav-item">
                                <button class="btn-dark" type="submit" value="UserImage" ><%= username %></button>
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
                                Upload image below and see the magic!

                            </p>
                        </center>
                    </div>
                </div>
            </div>
        </header>

        <!-- Page Content -->
        <div class="container">
            <form class="md-form" method="post" enctype="multipart/form-data" action="Style">
                <div class="row">
                    <div class="col-lg-12">

                        <div class="file-field">
                            <div class="btn btn-primary btn-sm float-left" style="width:100%; margin-bottom: 30px;">
                                <input class="btn btn-primary btn-sm float-left" style="width:100%;"type="file" name="file">

                            </div>
                        </div>

                    </div>

                </div>

                <!-- /.row -->

                <div class="row">
                    <div class="col-md-4 mb-5">
                        <div class="card h-75">
                            <img class="card-img-top" src="images/GrayScale.png" alt="">
                            <div class="card-body">
                                <center><h4 class="card-title">
                                        GrayScale</h4>

                                    <div class="form-check">
                                        <input type="checkbox" name="style_apply" value="grayscale" class="form-check-input" id="exampleCheck1">
                                        <label class="form-check-label" for="exampleCheck1">Check me out</label>
                                    </div>
                                </center>

                            </div>
                            <div class="card-footer">

                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 mb-5">
                        <div class="card h-75">
                            <img class="card-img-top" src="images/Negative.png" alt="">
                            <div class="card-body">
                                <center><h4 class="card-title">
                                        Negative</h4>

                                    <div class="form-check">
                                        <input type="checkbox" name="style_apply" value="negative" class="form-check-input" id="exampleCheck1">
                                        <label class="form-check-label" for="exampleCheck1">Check me out</label>
                                    </div>
                                </center>

                            </div>
                            <div class="card-footer">

                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 mb-5">
                        <div class="card h-75">
                            <img class="card-img-top" src="images/Sepia.png" alt="">
                            <div class="card-body">
                                <center><h4 class="card-title">
                                        Sepia</h4>

                                    <div class="form-check">
                                        <input type="checkbox" name="style_apply" value="sepia" class="form-check-input" id="exampleCheck1">
                                        <label class="form-check-label" for="exampleCheck1">Check me out</label>
                                    </div>
                                </center>

                            </div>
                            <div class="card-footer">

                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 mb-5">
                        <div class="card h-75">
                            <img class="card-img-top" src="images/Sharpen.png" alt="">
                            <div class="card-body">
                                <center><h4 class="card-title">
                                        Sharpen</h4>

                                    <div class="form-check">
                                        <input type="checkbox" name="style_apply" value="sharpen" class="form-check-input" id="exampleCheck1">
                                        <label class="form-check-label" for="exampleCheck1">Check me out</label>
                                    </div>
                                </center>

                            </div>
                            <div class="card-footer">

                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 mb-5">
                        <div class="card h-75">
                            <img class="card-img-top" src="images/B&W.png" alt="">
                            <div class="card-body">
                                <center><h4 class="card-title">
                                        B&W</h4>

                                    <div class="form-check">
                                        <input type="checkbox" name="style_apply" value="b&w" class="form-check-input" id="exampleCheck1">
                                        <label class="form-check-label" for="exampleCheck1">Check me out</label>
                                    </div>
                                </center>

                            </div>
                            <div class="card-footer">

                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 mb-5">
                        <div class="card h-75">
                            <img class="card-img-top" src="images/Borderline.png" alt="">
                            <div class="card-body">
                                <center><h4 class="card-title">
                                        Borderline</h4>

                                    <div class="form-check">
                                        <input type="checkbox" name="style_apply" value="borderline" class="form-check-input" id="exampleCheck1">
                                        <label class="form-check-label" for="exampleCheck1">Check me out</label>
                                    </div>
                                </center>

                            </div>
                            <div class="card-footer">

                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 mb-5">
                        <div class="card h-75">
                            <img class="card-img-top" src="images/Smooth.png" alt="">
                            <div class="card-body">
                                <center><h4 class="card-title">
                                        Smooth</h4>

                                    <div class="form-check">
                                        <input type="checkbox" name="style_apply" value="smooth" class="form-check-input" id="exampleCheck1">
                                        <label class="form-check-label" for="exampleCheck1">Check me out</label>
                                    </div>
                                </center>

                            </div>
                            <div class="card-footer">

                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 mb-5">
                        <div class="card h-75">
                            <img class="card-img-top" src="images/Hue.png" alt="">
                            <div class="card-body">
                                <center><h4 class="card-title">
                                        Hue</h4>

                                    <div class="form-check">
                                        <input type="checkbox" name="style_apply" value="hue" class="form-check-input" id="exampleCheck1">
                                        <label class="form-check-label" for="exampleCheck1">Check me out</label>
                                    </div>
                                </center>

                            </div>
                            <div class="card-footer">

                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 mb-5">
                        <div class="card h-75">
                            <img class="card-img-top" src="images/Salavi.png" alt="">
                            <div class="card-body">
                                <center><h4 class="card-title">
                                        Salavi</h4>

                                    <div class="form-check">
                                        <input type="checkbox" name="style_apply" value="salavi" class="form-check-input" id="exampleCheck1">
                                        <label class="form-check-label" for="exampleCheck1">Check me out</label>
                                    </div>
                                </center>

                            </div>
                            <div class="card-footer">

                            </div>
                        </div>
                    </div>

                </div>
                <!-- /.row -->

                <center>

                    <div style="margin-bottom: 30px;">
                        <button type="submit" class="btn btn-primary btn-lg" style="margin-bottom: 20px;">Upload</button>
                    </div>
                </center>
            </form>
        </div>
        <!-- /.container -->

        <!-- Footer -->
        <footer class="py-5 bg-dark">
            <div class="container">
                <p class="m-0 text-center text-white">One Last Project 2019</p>
            </div>
            <!-- /.container -->
        </footer>

        <!-- Bootstrap core JavaScript -->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    </body>

</html>