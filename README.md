# Yape Recipes

Aplicación desarrollada para el proceso de selección de Yape.

## Arquitectura
El proyecto fue creado usando la arquitectura MVVM. Se decidió así ya que es la arquitectura más usada para desarrollo android y debido a la versatilidad que ofrece se puede trabajar de manera eficiente y rápida, lo cual vino genial debido al limitado tiempo que se tenía para desarrollar la app.
Tomadas de la arquitectura clean también se crearon carpetas para las capas data y UI. Se debatió poner los Viewmodels junto a los Fragments en la carpeta "ui". Al final se decidió que esto daría más limpieza a la estructura del proyecto que si se hubieran puesto las carpetas de pantalla fuera de la carpeta "ui".

## Comportamiento
La aplicación recupera los datos de un mock creado en github que devuelve un payload con 10 recetas usando Retrofit 2. Estas recetas son mostradas en la app en un recycerview. La imagen es mostrada en un ImageView usando la dependencia "Glide". Al tocar una receta se puede acceder a los detalles de la misma, aquí se puede acceder a la pantalla de origen de la receta al tocar el botón "View on Map". Para desplegar la ubicación se utilizó la dependencia de "Google Maps".

## A resaltar

- Se decidió usar XML en vez de jetpack compose ya que tengo conocimientos de jetpack compose, pero estoy más habituado a trabajar en el día a día con XML. Dada la deadline de entrega de proyecto pensé qe sería más seguro utilizar XML.

- Para manejo de datos traídos del endpoint se utiliza Flow y Livedata. Habría sido más fácil utilizar simplemente Livedata pero quería hacer ver que también puedo hacer uso de Flow si se requiere en algún proyecto.

- Se utilizó mockito y mockk para las pruebas, esto debido a que en mi experiencia, algunas veces una librería no funciona con algunos tests y la otra sí. Esto me daba más opciones a la hora de escribir tests.

- Dejé a la vista mi key de google maps por temas de simplicidad y por la naturaleza del proyecto. Pero en un proyecto real se debe colocar este y otros datos confidenciales en un archivo encriptado "secrets.properties", el cual se debe desencriptar con una clave para poder ser usado y compilar el proyecto.

- Se crearon 2 configuraciones, una corre todos los test unitarios y la otra corre todos los tests instrumentados. Esto para facilidad de examinación del proyecto. Los nombres de las configuraciones son "All Android Tests" y "All Unit Tests"

## Dependencias de terceros utilizadas

- Jetpack navigation
- Retrofit2
- Kotlin Coroutines - Flow
- Dagger Hilt
- Google Maps
- Glide
- Lottie

## Dependencias utilizadas para Testing

- JUnit4
- Mockk
- Mockito
- Espresso
