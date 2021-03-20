# Aplicación Web Segura y Distribuída

[![danielrincon-m](https://circleci.com/gh/danielrincon-m/AREP_LAB4.svg?style=svg)](https://app.circleci.com/pipelines/github/danielrincon-m/AREP_LAB4)
<!-- [![Heroku](img/heroku_long.png)](https://nanospring.herokuapp.com/nspapp/register) -->

## Descripción 🔑

En este laboratorio desarrollaremos un proyecto web simple, en el cual los dos módulos que lo componen estarán corriendo en máquinas virtuales independientes en Amazon Web Services, ambas máquinas tendrán corriendo servidores de Spark Java, que estarán escuchando peticiones públicas. Los componentes del sistema se enumeran a continuación:

- Cliente, en este caso, nuestra máquina local.
- Un servicio llamado LoginService, este servicio publica un formulario accesible a través de un navegador Web en donde se deberán poner credenciales válidas para acceder al otro servicio.
- Un servicio llamado TimeService, que si es solicitado con las credenciales correctas, nos retornará la hora del servidor.

La arquitectura de los servicios se muestra a continuación, en donde OtherService representa nuestro TimeService:

![Arquitectura](img/arquitectura.png)


## Implementación 🛡️

La arquitectura de la aplicación se basa en comunicaciones cifradas vía HTTPS, para lograr esto, ambos servicios presentados tienen sus propios certificados de tipo "self-signed", y el servicio de Login cuenta con autorización para recibir datos de manera segura por parte del servicio de Time, esto se logró agregando el certificado de este último, a la lista de certificados válidos del servicio Login.

Vamos a realizar un breve recorrido por cada uno de los servicios.

### TimeService

Se trata de un servicio web simple que responde a una sola petición GET en la ruta '/time', el servicio espera dos parámetros, un usuario y una contraseña, por ahora los datos válidos están incrustados en el código, y deben ser enviados al servidor siempre que se desee realizar una petición, sin embargo una gran mejora consistiría en crear una sesión para los usuarios autenticados y a través de esta sesión, responder a la respuesta sin requerir los demás datos, además de almacenar las credenciales en un archivo o base de datos.

La contraseña que espera el LoginService debe estar codificada bajo el algoritmo de hash SHA-256, esta codificación es responsabilidad de la capa que llama al servicio. El servicio web está construido sobre [Spark Java](#herramientas-utilizadas-%EF%B8%8F), fué empaquetado y subido a la máquina virtual de AWS para su ejecución.

### LoginService

Este servicio presenta un formulario web, con campos para diligenciar un usuario y una contraseña, al diligenciar el formulario y enviarlo, se realiza una petición POST con los datos diligenciados, una vez llegan al servidor, la contraseña es cifrada bajo un algoritmo SHA-256, posterior a esto se envía una petición GET al servicio de Time con estos datos y se renderiza la respuesta del servicio en la vista del usuario.

## Video de demostración

Se realizó un video demostrando y explicando el funcionamiento de todo el sistema, este video puede ser encontrado [AQUÍ](Demostración Aplicación Web Segura.mp4).

## Descarga del proyecto ⬇️

Clone el proyecto utilizando el siguiente comando:

```
git clone https://github.com/danielrincon-m/AREP_LAB6.git
```

## Correr las pruebas unitarias 🧪

### Prerrequisitos

Un IDE que soporte proyectos Java, o una instalación de Maven en su sistema, puede obtenerlo desde
la [página oficial.][mvnLink]

### Ejecución de pruebas

Las pruebas pueden ser ejecutadas desde la sección de pruebas de su IDE o si tiene maven puede navegar a la carpeta
principal de cada uno de los dos proyectos internos y ejecutar el comando

```
mvn test
```

## Documentación del código fuente 🌎

La documentación de los proyectos puede ser encontrada en las carpetas [LoginService/docs](LoginService/docs) y [TimeService/docs](TimeService/docs).

También puede ser generada con Maven, clonando el proyecto y ejecutando el siguiente comando en cada una de las carpetas:

```
mvn javadoc:javadoc
```

## Documento de diseño 📄

Este laboratorio no cuenta con documento de diseño.

## Herramientas utilizadas 🛠️

* [Visual Studio Code](https://code.visualstudio.com/) - IDE de desarrollo
* [Maven](https://maven.apache.org/) - Manejo de Dependencias
* [JUnit](https://junit.org/junit4/) - Pruebas unitarias
* [GitHub](https://github.com/) - Repositorio de código
* [Spark](https://sparkjava.com/) - Framework web
* [AWS](https://aws.amazon.com/es/) - Despliegue en la nube

## Autor 🧔

**Daniel Felipe Rincón Muñoz:** *Planeación y desarrollo del proyecto* -
[Perfil de GitHub](https://github.com/danielrincon-m)

## Licencia 🚀

Este proyecto se encuentra licenciado bajo **GNU General Public License** - consulte el archivo [LICENSE.md](LICENSE.md)
para más detalles.

<!-- 
## Acknowledgments 

* Hat tip to anyone whose code was used
* Inspiration
* etc
-->

[gitLink]: https://git-scm.com/downloads
[mvnLink]: https://maven.apache.org/download.cgi
