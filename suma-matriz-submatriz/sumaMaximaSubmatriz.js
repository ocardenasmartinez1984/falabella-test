"use strict";

/**
 * Calcula la suma máxima de una submatriz 2x2 dentro de una matriz.
 * Implementación recursiva: recorre todas las posiciones (i, j) posibles
 * mediante recursión en lugar de bucles.
 *
 * @param {number[][]} matriz - Matriz de enteros (mínimo 2x2).
 * @returns {number} La suma máxima de una submatriz 2x2.
 */
function maxSumaSubmatriz2x2(matriz) {
  // Validación básica: la matriz debe tener al menos 2 filas y 2 columnas
  if (
    !Array.isArray(matriz) ||
    matriz.length < 2 ||
    !Array.isArray(matriz[0]) ||
    matriz[0].length < 2
  ) {
    throw new Error("La matriz debe ser de al menos 2x2.");
  }

  const filas = matriz.length;
  const columnas = matriz[0].length;

  /**
   * Suma los 4 elementos de la submatriz 2x2 cuya esquina superior
   * izquierda es (i, j).
   */
  function sumaVentana(i, j) {
    return (
      matriz[i][j] +
      matriz[i][j + 1] +
      matriz[i + 1][j] +
      matriz[i + 1][j + 1]
    );
  }

  /**
   * Recorre recursivamente todas las ventanas 2x2 posibles y devuelve
   * la suma máxima encontrada.
   */
  function recorrer(i, j) {
    // Caso base: nos pasamos de la última fila válida.
    if (i >= filas - 1) {
      return Number.NEGATIVE_INFINITY;
    }

    // Si nos pasamos de la última columna válida, saltamos a la siguiente fila.
    if (j >= columnas - 1) {
      return recorrer(i + 1, 0);
    }

    const sumaActual = sumaVentana(i, j);
    const restoFila = recorrer(i, j + 1);

    return Math.max(sumaActual, restoFila);
  }

  return recorrer(0, 0);
}

function main() {
  // Ejemplo:
  // [2, 5, 7]
  // [3, 4, 5]
  // [4, 1, 3]
  const matrizEjemplo = [
    [2, 5, 7],
    [3, 4, 5],
    [4, 1, 3],
  ];

  const resultado = maxSumaSubmatriz2x2(matrizEjemplo);
  console.log("La suma máxima de submatriz 2x2 es: " + resultado);
  // Imprime: 21
}

// Ejecuta main solo si el archivo se corre directamente.
if (require.main === module) {
  main();
}

module.exports = { maxSumaSubmatriz2x2 };
