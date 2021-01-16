package com.adonascimento.planetasapi.calculation;


public class Triangulo {

    private double x1,y1,x2,y2,x3, y3;
    private double y23, x32, y31, x13;
    private double det, minD, maxD;


       public Triangulo(double x1, double y1, double x2, double y2, double x3, double y3) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2= y2;
            this.x3 = x3;
            this.y3 = y3;
            y23 = y2 - y3;
            x32 = x3 - x2;
            y31 = y3 - y1;
            x13 = x1 - x3;
            det = y23 * x13 - x32 * y31;
            minD = Math.min(det, 0);
            maxD = Math.max(det, 0);
        }

       public boolean centroIncluido() {
            double dx = 0 - x3;
            double dy = 0 - y3;
            double a = y23 * dx + x32 * dy;
            if (a < minD || a > maxD) return false;
            double b = y31 * dx + x13 * dy;
            if (b < minD || b > maxD) return false;
            double c = det - a - b;
            if (c < minD || c > maxD) return false;
            return true;
        }

        public double perimetroTriangulo() {
           double segmentoAB = tamanioSegmentoRecta(x1,y1,x2,y2);
           double segmentoBC = tamanioSegmentoRecta(x2,y2,x3,y3);
           double segmentoAC = tamanioSegmentoRecta(x1,y1,x3,y3);
           return segmentoAB+segmentoBC+segmentoAC;


        }

        private double tamanioSegmentoRecta(double x1,double y1,double x2, double y2) {
           double deltaX = x2-x1;
           double deltaY = y2-y1;
           return Math.sqrt(Math.pow(deltaX,2)+Math.pow(deltaY,2));

        }
}

