**Búsqueda de Palabras Clave en Documentos .docx utilizando Árbol AVL**

### Introducción

El presente proyecto consiste en el desarrollo de un sistema capaz de indexar palabras clave contenidas en documentos `.docx`, utilizando para ello una estructura de datos tipo Árbol AVL. El sistema permite realizar búsquedas eficientes a través de una interfaz gráfica desarrollada en Java. Las búsquedas retornan los documentos donde se encuentra la palabra clave ingresada por el usuario, junto con estadísticas de búsqueda relevantes.

---

### Tecnologías Utilizadas

* **Lenguaje de programación:** Java
* **Gestor de construcción:** Apache Ant
* **IDE recomendado:** NetBeans
* **Interfaz gráfica:** Java Swing
* **Librerías externas:** Apache POI, Log4j, entre otras (detalladas más abajo)

---

### Estructura del Proyecto

* `src/` – Código fuente del sistema
* `documentos/` – Carpeta con archivos `.docx` utilizados como datos de prueba
* `lib/` – Carpeta que contiene los archivos `.jar` de las librerías externas
* `build.xml` – Script de construcción para Ant
* `nbproject/` – Archivos de configuración de NetBeans

---

### Requisitos para la Ejecución

1. Tener instalado Java JDK 8 o superior.
2. Disponer de un entorno de desarrollo compatible con proyectos Ant (preferiblemente NetBeans).
3. Agregar manualmente las dependencias externas (ubicadas en la carpeta `lib/`) al classpath del proyecto:

   * Ir a **Propiedades del proyecto > Librerías > Compilación > Classpath > Añadir JAR/Carpeta**
   * Seleccionar todas las librerías ubicadas en `lib/`
   * Aceptar y cerrar

Una vez completado este paso, el proyecto puede ejecutarse desde el entorno de desarrollo sin requerir configuración adicional.

---

### Funcionalidad del Sistema

#### 1. Indexación de Documentos

Durante la fase de inicialización, el sistema recorre todos los documentos `.docx` ubicados en la carpeta `documentos/`, extrae el texto contenido y lo procesa para construir el índice de palabras clave. Para ello:

* Se eliminan palabras comunes o de poco valor semántico (stopwords).
* Las palabras relevantes son insertadas en un Árbol AVL, junto con referencias a los documentos en los que aparecen.

#### 2. Búsqueda de Palabras Clave

A través de la interfaz gráfica, el usuario puede ingresar una palabra clave. Al realizar la búsqueda:

* El sistema consulta el Árbol AVL para determinar si la palabra se encuentra indexada.
* En caso afirmativo, muestra en pantalla una lista con los documentos que contienen dicha palabra.
* Adicionalmente, se muestra un conjunto de métricas relacionadas a la búsqueda realizada.

#### 3. Estadísticas de Búsqueda

Se recopilan y presentan los siguientes datos estadísticos:

* Cantidad de comparaciones realizadas para encontrar la palabra
* Tiempo promedio de búsqueda (en milisegundos)
* Número total de palabras indexadas
* Número de búsquedas realizadas durante la ejecución del programa

---

### Librerías Externas Utilizadas

Las siguientes bibliotecas se encuentran incluidas en el directorio `lib/`, pero **deben ser agregadas manualmente al classpath del proyecto** para que el sistema funcione correctamente:

* `poi-5.2.3.jar`
* `poi-ooxml-5.2.3.jar`
* `poi-ooxml-lite-5.2.3.jar`
* `xmlbeans-5.1.1.jar`
* `commons-collections4-4.4.jar`
* `commons-compress-1.27.1.jar`
* `commons-io-2.13.0.jar`
* `log4j-api-2.24.3.jar`
* `log4j-core-2.24.3.jar`

Estas bibliotecas son necesarias principalmente para el procesamiento de archivos `.docx` (Apache POI) y para el manejo de dependencias relacionadas con lectura, escritura, y logging.

---

### Ejecución del Proyecto

1. Clonar el repositorio:

   ```bash
   git clone https://github.com/Ssnake74/Busqueda_de_palabras_clave
   ```

2. Abrir el proyecto con NetBeans.

3. Agregar las librerías externas al classpath como se indica en la sección anterior.

4. Ejecutar el proyecto.

---
