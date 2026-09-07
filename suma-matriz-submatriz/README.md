# Suma Máxima de Submatriz 2x2

Programa en Node.js que calcula la **suma máxima de una submatriz 2x2** dentro de una matriz de enteros. La implementación es **recursiva**: recorre todas las ventanas 2x2 posibles sin usar bucles.

## Cómo funciona

Dada una matriz de al menos 2x2, se evalúan todas las submatrices contiguas de tamaño 2x2 y se devuelve la mayor suma de sus 4 elementos.

Ejemplo:

```
[2, 5, 7]
[3, 4, 5]
[4, 1, 3]
```

La submatriz con mayor suma es:

```
[5, 7]
[4, 5]  ->  5 + 7 + 4 + 5 = 21
```

Resultado: `21`.

## Requisitos

- [Node.js](https://nodejs.org/) 20 o superior, **o**
- [Docker](https://www.docker.com/)

## Ejecución con Node.js

```bash
node sumaMaximaSubmatriz.js
```

Salida esperada:

```
La suma máxima de submatriz 2x2 es: 21
```

## Ejecución con Docker

Construir la imagen:

```bash
docker build -t suma-submatriz .
```

Ejecutar el contenedor:

```bash
docker run --rm suma-submatriz
```

## Uso como módulo

La función se exporta y puede reutilizarse en otros archivos:

```js
const { maxSumaSubmatriz2x2 } = require("./sumaMaximaSubmatriz");

const matriz = [
  [2, 5, 7],
  [3, 4, 5],
  [4, 1, 3],
];

console.log(maxSumaSubmatriz2x2(matriz)); // 21
```

### API

`maxSumaSubmatriz2x2(matriz)`

- **Parámetro:** `matriz` — arreglo bidimensional de enteros (mínimo 2x2).
- **Retorna:** `number` — la suma máxima de una submatriz 2x2.
- **Lanza:** `Error` si la matriz es menor que 2x2.

## Estructura del proyecto

```
.
├── Dockerfile              # Imagen basada en node:20-alpine
├── sumaMaximaSubmatriz.js  # Lógica recursiva + ejemplo
└── README.md
```
