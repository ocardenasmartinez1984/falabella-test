```
  _____ _    _        ___ ___ _    _      _   ___ ___ _____
 |  ___/ \  | |      / \ | _ ) __| |    | |  | __/ __|_   _|
 | |_ / _ \ | |     / _ \|  _ \ _|| |__ | |__| _|\__ \ | |
 |  _/ ___ \| |___ / ___ \ |_) |__|____||____|___|___/ |_|
 |_|/_/   \_\_____/_/   \_\___/

   ┌───────────────────────────────────────────────────┐
   │        P R U E B A   T É C N I C A                  │
   │        Desafíos de Programación                     │
   └───────────────────────────────────────────────────┘
```

# Falabella Test

Este repositorio reúne **dos aplicaciones independientes** desarrolladas como
parte de una prueba técnica para una posición de trabajo. Cada proyecto resuelve
un problema distinto, con su propio stack tecnológico, pruebas y empaquetado en
Docker.

> Presentado como parte de un proceso de postulación. El objetivo es demostrar
> dominio de dos ecosistemas (Node.js y Java/Spring Boot), buenas prácticas de
> arquitectura, pruebas unitarias, cobertura y containerización.

---

## Contenido del repositorio

```
falabella-test/
├── suma-matriz-submatriz/   → App 1: Node.js  (algoritmo recursivo)
└── calculouf/               → App 2: Java / Spring Boot (microservicio REST)
```

| # | Proyecto | Stack | Tipo | Qué resuelve |
|---|----------|-------|------|--------------|
| 1 | [`suma-matriz-submatriz`](./suma-matriz-submatriz) | Node.js 20 | Script / algoritmo | Suma máxima de una submatriz 2×2 (implementación **recursiva**, sin bucles). |
| 2 | [`calculouf`](./calculouf) | Java 21 · Spring Boot 3.3 · Maven | Microservicio REST | Calcula el valor de N UF consultando indicadores económicos en tiempo real. |

---

## 1. suma-matriz-submatriz  🧮

```
 ┌─────────────────────────────┐
 │  Node.js · Algoritmo         │
 │  Suma máxima submatriz 2x2   │
 └─────────────────────────────┘
```

Programa en **Node.js** que calcula la **suma máxima de una submatriz 2×2**
dentro de una matriz de enteros. La lógica es **recursiva**: recorre todas las
ventanas 2×2 posibles sin usar bucles.

**Ejemplo:**

```
[2, 5, 7]        La submatriz de mayor suma es:   [5, 7]
[3, 4, 5]   -->                                   [4, 5]  = 5+7+4+5 = 21
[4, 1, 3]
```

### Uso

Requiere **Node.js 20+** o **Docker**.

```bash
cd suma-matriz-submatriz

# Con Node.js
node sumaMaximaSubmatriz.js
# -> La suma máxima de submatriz 2x2 es: 21

# Con Docker
docker build -t suma-submatriz .
docker run --rm suma-submatriz
```

También se puede reutilizar como módulo:

```js
const { maxSumaSubmatriz2x2 } = require("./sumaMaximaSubmatriz");
maxSumaSubmatriz2x2([[2,5,7],[3,4,5],[4,1,3]]); // 21
```

> Detalle completo (API, validaciones, estructura) en
> [`suma-matriz-submatriz/README.md`](./suma-matriz-submatriz/README.md).

---

## 2. calculouf  💰

```
 ┌─────────────────────────────┐
 │  Java 21 · Spring Boot 3     │
 │  Microservicio valor de UF   │
 └─────────────────────────────┘
```

**Microservicio REST** en **Spring Boot 3 / Java 21** que consume el endpoint de
indicadores económicos de [boostr.cl](https://api.boostr.cl/economy/indicators.json),
obtiene el valor actual de la **UF** y lo multiplica por la cantidad de UF que
entrega el cliente, devolviendo el resultado **sin decimales**.

Sigue una **arquitectura en tres capas**:

```
Cliente → Controller → Service → Data → API externa
          (HTTP)       (negocio) (fetch)  (boostr.cl)
```

### Endpoint

```
GET /calculouf?ufs={entero}
```

### Uso

Requiere **Docker** con el plugin Docker Compose. La compilación, las pruebas
unitarias y el reporte de cobertura JaCoCo ocurren **dentro** de la imagen
(build multi-stage), por lo que no necesitas Java ni Maven instalados.

```bash
cd calculouf

# Construir (compila + corre tests + genera cobertura)
docker compose build

# Levantar en segundo plano (usar siempre -d)
docker compose up --build -d

# Probar (espera a ver "Started CalculoufApplication" en los logs)
curl "http://localhost:1984/calculouf?ufs=3"
# -> 122649   (valorUf * 3, sin decimales)

# Detener
docker compose down
```

> IMPORTANTE: usa siempre `-d`. Sin él, el contenedor queda atado a la terminal
> y se detiene al cerrar la sesión.

> Detalle completo (diagrama de secuencia, cobertura JaCoCo, solución de
> problemas) en [`calculouf/README.md`](./calculouf/README.md).

---

## Resumen rápido

| Acción | suma-matriz-submatriz | calculouf |
|--------|-----------------------|-----------|
| Ejecutar | `node sumaMaximaSubmatriz.js` | `docker compose up --build -d` |
| Con Docker | `docker build -t suma-submatriz . && docker run --rm suma-submatriz` | `docker compose build` |
| Puerto | — (script) | `1984` |
| Pruebas | — | Automáticas durante el build de la imagen |

---

```
        Gracias por revisar este trabajo.
   ── Node.js · Java · Spring Boot · Docker · Testing ──
```
