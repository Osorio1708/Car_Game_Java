-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 18-07-2021 a las 21:42:12
-- Versión del servidor: 10.4.20-MariaDB
-- Versión de PHP: 8.0.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `sofkacar`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `player`
--

CREATE TABLE `player` (
  `idPlayer` varchar(10) NOT NULL,
  `namePlayer` varchar(10) DEFAULT NULL,
  `numFirst` int(6) DEFAULT NULL,
  `numSec` int(6) DEFAULT NULL,
  `numTrd` int(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `player`
--

INSERT INTO `player` (`idPlayer`, `namePlayer`, `numFirst`, `numSec`, `numTrd`) VALUES
('1234', 'Juan', 1, 2, 1),
('2021', 'Juanito', 1, 2, 2),
('2121', 'Penelope', 0, 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `podium`
--

CREATE TABLE `podium` (
  `codPodium` int(6) NOT NULL,
  `date` datetime DEFAULT NULL,
  `firstPlace` varchar(10) DEFAULT NULL,
  `secondPlace` varchar(10) DEFAULT NULL,
  `thirdPlace` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `podium`
--

INSERT INTO `podium` (`codPodium`, `date`, `firstPlace`, `secondPlace`, `thirdPlace`) VALUES
(6, '2021-07-17 23:53:08', 'Kira', 'Juanito', 'Juan'),
(7, '2021-07-18 00:07:50', 'Juan', 'Santiago', 'Kira'),
(8, '2021-07-18 11:04:45', 'Kira', 'Juanito', 'Santiago'),
(9, '2021-07-18 11:06:24', 'Kira', 'Juan', 'Juanito'),
(10, '2021-07-18 11:07:08', 'Kira', 'Juanito', 'Santiago'),
(11, '2021-07-18 11:47:43', 'Santiago', 'Juan', 'Juanito'),
(12, '2021-07-18 13:58:40', 'Juanito', 'Penelope', 'Juan'),
(13, '2021-07-18 14:30:48', 'Juan', 'Juanito', 'Penelope');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `player`
--
ALTER TABLE `player`
  ADD PRIMARY KEY (`idPlayer`);

--
-- Indices de la tabla `podium`
--
ALTER TABLE `podium`
  ADD PRIMARY KEY (`codPodium`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `podium`
--
ALTER TABLE `podium`
  MODIFY `codPodium` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
