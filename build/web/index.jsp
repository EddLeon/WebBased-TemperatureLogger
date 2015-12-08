<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<%@ page import="java.util.*" %>
<%@ page import="java.net.*" %>
<%@ page import="java.net.*" %>
<%@ page import="javax.servlet.*"%>
<%@ page import="javax.servlet.http.*"%>

<%@ page import = "javax.servlet.ServletException"%>
<%@ page import ="javax.servlet.http.HttpServlet"%>

<html>
    <head>
        <title>Temperature Logger</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <script src="gauge.min.js"></script>
        <script src="jquery.min.js" type="text/javascript"></script>
        <script src="highcharts.js" type="text/javascript"></script>
        <script src="jquery.highchartTable.js" type="text/javascript"></script>
        <script src="http://code.jquery.com/jquery-latest.min.js"></script>
        <script src="https://cdn.plot.ly/plotly-latest.min.js"></script>
    </head>
    <body>
        <div>Proyecto Final Java</div>
        <!--<a href="dataBridge?temperature=-1">EnviarTemperatra</a> -->
       
       <canvas id="gauge" width="400" height="400"></canvas>
        <script>
            var gauge = new Gauge({
                    renderTo    : 'gauge',
                    width       : 400,
                    height      : 400,
                    glow        : true,
                    units       : '°C',
                    title       : 'Temperature',
                    minValue    : -10,
                    maxValue    : 30,
                    majorTicks  : ['-10','0','10','20','30'],
                    minorTicks  : 10,
                    strokeTicks : false,
                    highlights  : [
                            { from : -10, to : 5, color : 'rgba(0,   0, 255, .3)' },
                            { from : 5, to : 30, color : 'rgba(255, 0, 0, .3)' }
                    ],
                    colors      : {
                            plate      : '#f5f5f5',
                            majorTicks : '#000',
                            minorTicks : '#222',
                            title      : '#222',
                            units      : '#666',
                            numbers    : '#222',
                            needle     : { start : 'rgba(240, 128, 128, 1)', end : 'rgba(255, 160, 122, .9)' }
                    },
                    animation : {
                            delay : 25,
                            duration: 500,
                            fn : 'bounce'
                    }
            });

            gauge.onready = function() {
                    setInterval( function() {
                            getTemp();
                    }, 15000);
            };
            
            
            gauge.draw();
            
        
            var req;
            var res;
            function getTemp()
            {
                req = new XMLHttpRequest();
		req.onload = actualizar;
		req.open("GET", "dataBridge?temperature=-1",true);
		req.send();
                //var xmlHttp = new XMLHttpRequest();
                /*
                xmlHttp.onreadystatechange = function() { 
                    if (xmlHttp.readyState == 4 && xmlHttp.status == 200)
                        callback(xmlHttp.responseText);
                }*/
                //xmlHttp.open("GET", "dataBridge?temperature = -1", false); // true for asynchronous 
                //xmlHttp.send();
                   
                 
    
               }
               
            function actualizar(){
                espacio = document.getElementById("temp");
                var t = req.responseText;
		//alert(t);
                if(t==="a" || isNaN(t)){
                    espacio.innerHTML = "Nan: Error en el Sensor";
                    gauge.setValue(-10);
                }
                else{
                    gauge.setValue(t);
                    espacio.innerHTML = "";
                }
                           
                
            }
            function grafica(){
                var m = document.getElementById("mes");
                var mes = m.options[m.selectedIndex].value;
                var d = document.getElementById("dia");
                var dia= d.options[d.selectedIndex].value;
                
                res = new XMLHttpRequest();
		res.onload = actualizarTabla;
		res.open("GET", "obtieneTepmp?mes="+mes+"&dia="+dia ,true);
		res.send();
                
                /*var data = [{
                    x: [],
                    y: [res.responseText],
                    
                    type: 'bar'
                }];*/

                //Plotly.newPlot('myDiv', data);
                
            }
            function actualizarTabla(){
                document.getElementById("datos").innerHTML = res.responseText;
                 //$('table.highchart').dataTable();
            }
         </script>
         
        
        <h2 id='temp'></h2>
         <!--<form action="obtieneTepmp" method="POST"> -->
         <div>
           <select id = "mes">
               <option value="1">Enero</option>
               <option value="2">Febrero</option>
               <option value="3">Marzo</option>
               <option value="4">Abril</option>
               <option value="5">Mayo</option>
               <option value="6">Junio</option>
               <option value="7">Julio</option>
               <option value="8">Agosto</option>
               <option value="9">Septiembre</option>
               <option value="10">Octubre</option>
               <option value="11">Noviembre</option>
               <option value="12">Diciembre</option>
               </select>
             <select id = "dia">
               <option value="1">1</option>
               <option value="2">2</option>
               <option value="3">3</option>
               <option value="4">4</option>
               <option value="5">5</option>
               <option value="6">6</option>
               <option value="7">7</option>
               <option value="8">8</option>
               <option value="9">9</option>
               <option value="10">10</option>
               <option value="11">11</option>
               <option value="12">12</option>
               <option value="13">13</option>
               <option value="13">14</option>
               <option value="14">15</option>
               <option value="15">16</option>
               <option value="16">17</option>
               <option value="17">18</option>
               <option value="19">19</option>
               <option value="20">20</option>
               <option value="21">21</option>
               <option value="22">22</option>
               <option value="23">23</option>
               <option value="24">24</option>
               <option value="24">25</option>
               <option value="26">26</option>
               <option value="27">27</option>
               <option value="28">28</option>
               <option value="29">29</option>
               <option value="30">30</option>
               <option value="31">31</option>
               </select>
               <br/>
               <button onClick="grafica()" >Mostrar </button>

         </div> 
        <div id="datos"></div>
        <div id="myDiv" style="width: 480px; height: 400px;"><!-- Plotly chart will be drawn inside this DIV --></div>
        
    </body>
</html>
