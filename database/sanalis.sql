-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  Dim 26 avr. 2020 à 21:41
-- Version du serveur :  5.7.23
-- Version de PHP :  7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `sanalis`
--
CREATE DATABASE IF NOT EXISTS `sanalis` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
USE `sanalis`;

-- --------------------------------------------------------

--
-- Structure de la table `medecin`
--

DROP TABLE IF EXISTS `medecin`;
CREATE TABLE IF NOT EXISTS `medecin` (
  `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Medecin_name` varchar(255) COLLATE utf8_bin NOT NULL,
  `Medecin_firstname` varchar(255) COLLATE utf8_bin NOT NULL,
  `Medecin_email` varchar(255) COLLATE utf8_bin NOT NULL,
  `Medecin_phone` varchar(11) COLLATE utf8_bin NOT NULL,
  `Medecin_profession` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `medecin`
--

INSERT INTO `medecin` (`ID`, `Medecin_name`, `Medecin_firstname`, `Medecin_email`, `Medecin_phone`, `Medecin_profession`) VALUES
(2, 'Mylis', 'Mola', 'mola@mail.fr', '0565458547', 'Médecin Généraliste'),
(3, 'Diallo', 'Symis', 'diallo@gmail.com', '0652125478', 'Docteur'),
(4, 'Toreno', 'Sofia', 's.toreno@gmail.com', '0645879556', 'Ostéopathe');

-- --------------------------------------------------------

--
-- Structure de la table `medicament`
--

DROP TABLE IF EXISTS `medicament`;
CREATE TABLE IF NOT EXISTS `medicament` (
  `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Libelle` int(11) NOT NULL,
  `Med_code` varchar(30) COLLATE utf8_bin NOT NULL,
  `Name` varchar(30) COLLATE utf8_bin NOT NULL,
  `Expi_date` varchar(30) COLLATE utf8_bin NOT NULL,
  `Laboratory` varchar(30) COLLATE utf8_bin NOT NULL,
  `Description` text COLLATE utf8_bin NOT NULL,
  `Quantity` int(12) NOT NULL,
  `Quantity_type` varchar(5) COLLATE utf8_bin NOT NULL,
  `Med_type` varchar(255) COLLATE utf8_bin NOT NULL,
  `Med_use` text COLLATE utf8_bin NOT NULL,
  `Price` int(11) NOT NULL,
  `Toxicity` varchar(255) COLLATE utf8_bin NOT NULL,
  `Quantity_stock` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `medicament`
--

INSERT INTO `medicament` (`ID`, `Libelle`, `Med_code`, `Name`, `Expi_date`, `Laboratory`, `Description`, `Quantity`, `Quantity_type`, `Med_type`, `Med_use`, `Price`, `Toxicity`, `Quantity_stock`) VALUES
(5, 684, 'RL4354', 'Spasfon', '22-01-2022', 'Spasn Inc.', 'Médicament contre les maux de ventre', 1000, 'mg', 'N/A', 'Voie orale', 120, 'Niveaux 1', 3),
(8, 903874, '34009217', 'Desloratadine', '01-04-2022', 'Zentiva', 'Médicament contre les allergies ', 5, 'mg', 'Comprimé pelliculé', 'Voie orale', 15, 'Niveaux 1', 3),
(9, 65435, '55458', 'Doliprane', '29-04-2022', 'Sanofi', 'Médicament contre les douleurs ', 1000, 'mg', 'Solution à diluer', 'Voie orale', 25, 'Niveaux 1', 5);

-- --------------------------------------------------------

--
-- Structure de la table `patient`
--

DROP TABLE IF EXISTS `patient`;
CREATE TABLE IF NOT EXISTS `patient` (
  `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Patient_name` varchar(30) COLLATE utf8_bin NOT NULL,
  `Patient_firstname` varchar(30) COLLATE utf8_bin NOT NULL,
  `Patient_email` varchar(50) COLLATE utf8_bin NOT NULL,
  `Patient_phone` varchar(11) COLLATE utf8_bin NOT NULL,
  `Patient_phone_fixe` varchar(11) COLLATE utf8_bin NOT NULL,
  `Patient_address` varchar(255) COLLATE utf8_bin NOT NULL,
  `Patient_secu` varchar(25) COLLATE utf8_bin NOT NULL,
  `Patient_mutuelle` varchar(30) COLLATE utf8_bin NOT NULL,
  `Patient_genre` varchar(10) COLLATE utf8_bin NOT NULL,
  `Patient_grpSanguin` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `patient`
--

INSERT INTO `patient` (`ID`, `Patient_name`, `Patient_firstname`, `Patient_email`, `Patient_phone`, `Patient_phone_fixe`, `Patient_address`, `Patient_secu`, `Patient_mutuelle`, `Patient_genre`, `Patient_grpSanguin`) VALUES
(15, 'John', 'Karim', 'John@mail.fr', '0603828292', '0183746294', '45 rue Paris', '8675475SQ654387', 'Groupama', 'Autres', 'AB+'),
(20, 'Farinha', 'Djogo', 'd.farinha@gmail.com', '0715863547', '0185486895', '57 rue de provence ', '188127525486199', 'crédit agricole', 'Homme', 'AB+');

-- --------------------------------------------------------

--
-- Structure de la table `rdv`
--

DROP TABLE IF EXISTS `rdv`;
CREATE TABLE IF NOT EXISTS `rdv` (
  `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Id_patient` int(11) NOT NULL,
  `Id_medecin` int(11) NOT NULL,
  `Date_RDV` varchar(50) COLLATE utf8_bin NOT NULL,
  `Begin_hour` varchar(6) COLLATE utf8_bin NOT NULL,
  `Address_RDV` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `Id_patient` (`Id_patient`),
  KEY `Id_medecin` (`Id_medecin`)
) ENGINE=MyISAM AUTO_INCREMENT=87 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `rdv`
--

INSERT INTO `rdv` (`ID`, `Id_patient`, `Id_medecin`, `Date_RDV`, `Begin_hour`, `Address_RDV`) VALUES
(86, 15, 2, '27-04-2020', '16H00', 'dsd'),
(85, 20, 2, '27-04-2020', '14H00', 'Rue Hidel'),
(84, 15, 2, '27-04-2020', '13H00', 'Rue Hidel'),
(83, 20, 2, '27-04-2020', '12H00', 'Rue Hidel'),
(82, 15, 2, '27-04-2020', '11H00', 'Rue Hidel'),
(81, 20, 4, '27-04-2020', '15H00', 'Rue Hidel'),
(80, 15, 4, '27-04-2020', '14H00', 'Rue Hidel'),
(79, 20, 4, '27-04-2020', '12H00', 'Rue Hidel'),
(78, 15, 4, '27-04-2020', '11H00', 'Rue Hidel'),
(77, 15, 2, '27-04-2020', '10H00', 'Rue Hidel'),
(76, 20, 2, '27-04-2020', '09H00', 'Rue Hidel'),
(75, 20, 3, '27-04-2020', '11H00', 'Rue Adle'),
(74, 20, 3, '27-04-2020', '10H00', 'Rue Adle'),
(73, 20, 3, '27-04-2020', '09H00', 'Rue Adle');

-- --------------------------------------------------------

--
-- Structure de la table `register`
--

DROP TABLE IF EXISTS `register`;
CREATE TABLE IF NOT EXISTS `register` (
  `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Name` varchar(30) COLLATE utf8_bin NOT NULL,
  `Firstname` varchar(30) COLLATE utf8_bin NOT NULL,
  `Email` varchar(50) COLLATE utf8_bin NOT NULL,
  `Password` varchar(15) COLLATE utf8_bin NOT NULL,
  `Phone` varchar(10) COLLATE utf8_bin NOT NULL,
  `Role` varchar(12) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `register`
--

INSERT INTO `register` (`ID`, `Name`, `Firstname`, `Email`, `Password`, `Phone`, `Role`) VALUES
(1, 'Margaux ', 'Hebert', 'admin@admin.fr', 'admin', '0687342178', 'ADMIN'),
(2, 'Dane', 'Connor', 'c.dane@gmail.com', 'stock1', '0909090904', 'GESTIOSTOCK'),
(3, 'Berthe', 'Jeanne', 'j.berthe@gmail.com', 'rdv1', '0565567844', 'GESTIORDV'),
(10, 'Gérard', 'Claude', 'c.gerard@gmail.com', 'rdv2', '0145869874', 'GESTIORDV');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
