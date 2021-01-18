Asunciones

1. A modo práctico un año tiene 360 dias que son los grados de una circunferencia.
2. El sentido horario lo tomo como negativo y el antihorario como positivo.
3. Cuando el sol no está dentro del triangulo asumo clima desconocido y cuando está sobre uno de los lados del triangulo está dentro
4. El sistema puede escalar a agregarle más planetas, para esto es necesario agregar un método más en PlanetasFactory, pero ya habria que idear una lógica para ver si el (0,0) está dentro de la figura que formen entre los planetas
5. Los tres planetas comienzan alineados en el ángulo cero y esto es el dia 1.
6. El sol está en el punto (0,0).
7. El dia 1 puede ser cualquiera en el cálculo. Si el cálculo lo hago hoy va a dar como resultado seco. Esto es a modo práctico

Resolución.

1. Para saber si tres puntos en una circunferencia están alineados use trigonometría. Si el módulo del seno o coseno del ángulo barrido es el mismo significa que están alineados con respecto al sol
2. Para saber si los tres planetas están alineados entre sí pero no con respecto al sol saqué la pendiente de la recta que uno cualquiera de los dos puntos y luego compruebo si el tercer punto pertenece a esa recta y que el (0,0) no pertenezca.
3. Para Saber si un punto está dentro de un triángulo formado por tres puntos utilicé un código que encontré en internet. El sistema escala a más de tres panetas, pero está limitado a que forme una figura de un triagulo entre tres.
4. Agregué un atributo más para informar la cantidad de milimetros caidos en dias lluvisos y lo calculé como el perímetro del rectángulo formado por los tres planetas.
5. Los datos están persistidos en memoria. Una vez que inicia la aplicación se genera automáticamente el pronostico extendido del tiempo para los próximos 10 años.

Cómo levantar la aplicación

1. La aplicación es Spring boot y levanta desde la clase src/main/java/com.adonascimento.planetasapi/PlanetasApiApplication.java
2. Por defecto levanta en el puerto 8083.
3. Está documentada con swagger para mejor entendimiento de los servicios que expone en http://localhost:8083/swagger-ui.html
4. Para obtener la cantidad de periodos de lluvia, sequía y optimos resolví exponerlos como servicios y consultarlos.

Problemas que tuve

Deployé la aplicación en App Engine en la url https://planetas-api-302115.uc.r.appspot.com/ , pero por algún motivo que descnoozco no logré hacerla funcionar. 
No tengo experiencia en devops y me apoyé con tutoriales para poder hacerlo funcionar, pero quizás requiera más tiempo de pruebas.