-- phpMyAdmin SQL Dump
-- version 4.2.12deb2+deb8u2
-- http://www.phpmyadmin.net
--
-- Client :  localhost
-- Généré le :  Mer 31 Mai 2017 à 12:07
-- Version du serveur :  5.5.55-0+deb8u1
-- Version de PHP :  5.6.30-0+deb8u1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `PTS2`
--
CREATE DATABASE IF NOT EXISTS `PTS2` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `PTS2`;

-- --------------------------------------------------------

--
-- Structure de la table `Botteleuse`
--

DROP TABLE IF EXISTS `Botteleuse`;
CREATE TABLE IF NOT EXISTS `Botteleuse` (
  `Id_Bot` int(11) NOT NULL,
  `Id_ModBot` int(11) DEFAULT NULL,
  `Etat_Bot` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `Champ`
--

DROP TABLE IF EXISTS `Champ`;
CREATE TABLE IF NOT EXISTS `Champ` (
  `Id_Ch` int(11) NOT NULL,
  `Adr_Ch` varchar(100) NOT NULL,
  `Surf_Ch` int(11) NOT NULL,
  `Lat_Ch` float NOT NULL,
  `Long_Ch` float NOT NULL,
  `Id_TypCult` int(11) NOT NULL,
  `Id_Cli` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `Client`
--

DROP TABLE IF EXISTS `Client`;
CREATE TABLE IF NOT EXISTS `Client` (
  `Id_Cli` int(11) NOT NULL,
  `Nom_Cli` varchar(50) NOT NULL,
  `Prenom_Cli` varchar(50) DEFAULT NULL,
  `Num_Cli` int(11) NOT NULL,
  `Adr_Cli` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `Machine`
--

DROP TABLE IF EXISTS `Machine`;
CREATE TABLE IF NOT EXISTS `Machine` (
  `Id_Mach` int(11) NOT NULL,
  `Id_Tract` int(11) DEFAULT NULL,
  `Id_Bot` int(11) DEFAULT NULL,
  `Id_Moi` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `Marque`
--

DROP TABLE IF EXISTS `Marque`;
CREATE TABLE IF NOT EXISTS `Marque` (
  `Id_Marq` int(11) NOT NULL,
  `Nom_Marq` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `ModeleBotteleuse`
--

DROP TABLE IF EXISTS `ModeleBotteleuse`;
CREATE TABLE IF NOT EXISTS `ModeleBotteleuse` (
  `Id_ModBot` int(11) NOT NULL,
  `Id_Marq` int(11) DEFAULT NULL,
  `Nom_ModBot` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `ModeleMoissonneuse`
--

DROP TABLE IF EXISTS `ModeleMoissonneuse`;
CREATE TABLE IF NOT EXISTS `ModeleMoissonneuse` (
  `Id_ModMoi` int(11) NOT NULL,
  `Id_Marq` int(11) DEFAULT NULL,
  `Nom_ModMoi` varchar(50) NOT NULL,
  `ConsRoute_ModMoi` float NOT NULL,
  `ConsFonc_ModMoi` float NOT NULL,
  `CapaRes_ModMoi` int(11) NOT NULL,
  `LargRou_ModMoi` float NOT NULL,
  `Haut_ModMoi` float NOT NULL,
  `TaTrem_ModMoi` int(11) NOT NULL,
  `Poids_ModMoi` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `ModeleTracteur`
--

DROP TABLE IF EXISTS `ModeleTracteur`;
CREATE TABLE IF NOT EXISTS `ModeleTracteur` (
  `Id_ModTract` int(11) NOT NULL,
  `Id_Marq` int(11) DEFAULT NULL,
  `Nom_ModTract` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `Moissonneuse`
--

DROP TABLE IF EXISTS `Moissonneuse`;
CREATE TABLE IF NOT EXISTS `Moissonneuse` (
  `Id_Moi` int(11) NOT NULL,
  `Id_ModMoi` int(11) DEFAULT NULL,
  `Etat_Moi` int(11) NOT NULL,
  `LarCou_Moi` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `Point`
--

DROP TABLE IF EXISTS `Point`;
CREATE TABLE IF NOT EXISTS `Point` (
  `Id_Point` int(11) NOT NULL,
  `Lat_Point` float NOT NULL,
  `Long_Point` float NOT NULL,
  `Id_Ch` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `Recolte`
--

DROP TABLE IF EXISTS `Recolte`;
CREATE TABLE IF NOT EXISTS `Recolte` (
`Id_Rec` int(11) NOT NULL,
  `Date_Rec` date NOT NULL,
  `Fourchette_Rec` int(1) NOT NULL DEFAULT '0' COMMENT '0 = matin, 1 = après midi',
  `TMax_Rec` float NOT NULL DEFAULT '0',
  `Quant_Rec` float DEFAULT '0',
  `Cout_Rec` float DEFAULT '0',
  `Id_Cli` int(11) NOT NULL,
  `Id_Ch` int(11) NOT NULL,
  `Id_Trans` int(11) NOT NULL,
  `Id_TypeBot` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `RecolteMachine`
--

DROP TABLE IF EXISTS `RecolteMachine`;
CREATE TABLE IF NOT EXISTS `RecolteMachine` (
  `Id_Rec` int(11) NOT NULL,
  `Id_Mach` int(11) NOT NULL,
  `Dist_RecMach` char(1) NOT NULL,
  `Dur_RecMach` char(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `Tracteur`
--

DROP TABLE IF EXISTS `Tracteur`;
CREATE TABLE IF NOT EXISTS `Tracteur` (
  `Id_Tract` int(11) NOT NULL,
  `Id_ModTract` int(11) DEFAULT NULL,
  `Cap_Tract` float NOT NULL,
  `Etat_Tract` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `Transport`
--

DROP TABLE IF EXISTS `Transport`;
CREATE TABLE IF NOT EXISTS `Transport` (
  `Id_Trans` int(11) NOT NULL,
  `Nom_Trans` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `TypeBottelage`
--

DROP TABLE IF EXISTS `TypeBottelage`;
CREATE TABLE IF NOT EXISTS `TypeBottelage` (
  `Id_TypeBot` int(11) NOT NULL,
  `Nom_TypeBot` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `TypeCulture`
--

DROP TABLE IF EXISTS `TypeCulture`;
CREATE TABLE IF NOT EXISTS `TypeCulture` (
  `Id_TypCult` int(11) NOT NULL,
  `Nom_TypCult` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `VitesseCulture`
--

DROP TABLE IF EXISTS `VitesseCulture`;
CREATE TABLE IF NOT EXISTS `VitesseCulture` (
  `Id_TypCult` int(11) NOT NULL,
  `Id_Moi` int(11) NOT NULL,
  `Vit_VitCult` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Index pour les tables exportées
--

--
-- Index pour la table `Botteleuse`
--
ALTER TABLE `Botteleuse`
 ADD PRIMARY KEY (`Id_Bot`), ADD UNIQUE KEY `FKR_5_ID` (`Id_ModBot`);

--
-- Index pour la table `Champ`
--
ALTER TABLE `Champ`
 ADD PRIMARY KEY (`Id_Ch`), ADD KEY `FKR_7` (`Id_TypCult`), ADD KEY `FKR_6` (`Id_Cli`);

--
-- Index pour la table `Client`
--
ALTER TABLE `Client`
 ADD PRIMARY KEY (`Id_Cli`);

--
-- Index pour la table `Machine`
--
ALTER TABLE `Machine`
 ADD PRIMARY KEY (`Id_Mach`), ADD UNIQUE KEY `FKR_16_ID` (`Id_Tract`), ADD UNIQUE KEY `FKR_17_ID` (`Id_Bot`), ADD UNIQUE KEY `FKR_15_ID` (`Id_Moi`);

--
-- Index pour la table `Marque`
--
ALTER TABLE `Marque`
 ADD PRIMARY KEY (`Id_Marq`);

--
-- Index pour la table `ModeleBotteleuse`
--
ALTER TABLE `ModeleBotteleuse`
 ADD PRIMARY KEY (`Id_ModBot`), ADD KEY `FKR_2_ID` (`Id_Marq`);

--
-- Index pour la table `ModeleMoissonneuse`
--
ALTER TABLE `ModeleMoissonneuse`
 ADD PRIMARY KEY (`Id_ModMoi`), ADD KEY `FKR_1_ID` (`Id_Marq`);

--
-- Index pour la table `ModeleTracteur`
--
ALTER TABLE `ModeleTracteur`
 ADD PRIMARY KEY (`Id_ModTract`), ADD KEY `FKR_3_ID` (`Id_Marq`);

--
-- Index pour la table `Moissonneuse`
--
ALTER TABLE `Moissonneuse`
 ADD PRIMARY KEY (`Id_Moi`), ADD UNIQUE KEY `FKR_ID` (`Id_ModMoi`);

--
-- Index pour la table `Point`
--
ALTER TABLE `Point`
 ADD PRIMARY KEY (`Id_Point`), ADD KEY `FKR_10` (`Id_Ch`);

--
-- Index pour la table `Recolte`
--
ALTER TABLE `Recolte`
 ADD PRIMARY KEY (`Id_Rec`), ADD KEY `FKR_11` (`Id_Cli`), ADD KEY `FKR_13` (`Id_Ch`), ADD KEY `FKR_14` (`Id_Trans`), ADD KEY `FKR_12` (`Id_TypeBot`);

--
-- Index pour la table `RecolteMachine`
--
ALTER TABLE `RecolteMachine`
 ADD PRIMARY KEY (`Id_Mach`,`Id_Rec`), ADD KEY `FKId_Rec` (`Id_Rec`);

--
-- Index pour la table `Tracteur`
--
ALTER TABLE `Tracteur`
 ADD PRIMARY KEY (`Id_Tract`), ADD UNIQUE KEY `FKR_4_ID` (`Id_ModTract`);

--
-- Index pour la table `Transport`
--
ALTER TABLE `Transport`
 ADD PRIMARY KEY (`Id_Trans`);

--
-- Index pour la table `TypeBottelage`
--
ALTER TABLE `TypeBottelage`
 ADD PRIMARY KEY (`Id_TypeBot`);

--
-- Index pour la table `TypeCulture`
--
ALTER TABLE `TypeCulture`
 ADD PRIMARY KEY (`Id_TypCult`);

--
-- Index pour la table `VitesseCulture`
--
ALTER TABLE `VitesseCulture`
 ADD PRIMARY KEY (`Id_Moi`,`Id_TypCult`), ADD KEY `FKId_TypCult` (`Id_TypCult`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `Recolte`
--
ALTER TABLE `Recolte`
MODIFY `Id_Rec` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=23;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `Botteleuse`
--
ALTER TABLE `Botteleuse`
ADD CONSTRAINT `FKR_5_FK` FOREIGN KEY (`Id_ModBot`) REFERENCES `ModeleBotteleuse` (`Id_ModBot`);

--
-- Contraintes pour la table `Champ`
--
ALTER TABLE `Champ`
ADD CONSTRAINT `FKR_6` FOREIGN KEY (`Id_Cli`) REFERENCES `Client` (`Id_Cli`),
ADD CONSTRAINT `FKR_7` FOREIGN KEY (`Id_TypCult`) REFERENCES `TypeCulture` (`Id_TypCult`);

--
-- Contraintes pour la table `Machine`
--
ALTER TABLE `Machine`
ADD CONSTRAINT `FKR_15_FK` FOREIGN KEY (`Id_Moi`) REFERENCES `Moissonneuse` (`Id_Moi`),
ADD CONSTRAINT `FKR_16_FK` FOREIGN KEY (`Id_Tract`) REFERENCES `Tracteur` (`Id_Tract`),
ADD CONSTRAINT `FKR_17_FK` FOREIGN KEY (`Id_Bot`) REFERENCES `Botteleuse` (`Id_Bot`);

--
-- Contraintes pour la table `ModeleBotteleuse`
--
ALTER TABLE `ModeleBotteleuse`
ADD CONSTRAINT `FKR_2_FK` FOREIGN KEY (`Id_Marq`) REFERENCES `Marque` (`Id_Marq`);

--
-- Contraintes pour la table `ModeleMoissonneuse`
--
ALTER TABLE `ModeleMoissonneuse`
ADD CONSTRAINT `FKR_1_FK` FOREIGN KEY (`Id_Marq`) REFERENCES `Marque` (`Id_Marq`);

--
-- Contraintes pour la table `ModeleTracteur`
--
ALTER TABLE `ModeleTracteur`
ADD CONSTRAINT `FKR_3_FK` FOREIGN KEY (`Id_Marq`) REFERENCES `Marque` (`Id_Marq`);

--
-- Contraintes pour la table `Moissonneuse`
--
ALTER TABLE `Moissonneuse`
ADD CONSTRAINT `FKR_FK` FOREIGN KEY (`Id_ModMoi`) REFERENCES `ModeleMoissonneuse` (`Id_ModMoi`);

--
-- Contraintes pour la table `Point`
--
ALTER TABLE `Point`
ADD CONSTRAINT `FKR_10` FOREIGN KEY (`Id_Ch`) REFERENCES `Champ` (`Id_Ch`);

--
-- Contraintes pour la table `Recolte`
--
ALTER TABLE `Recolte`
ADD CONSTRAINT `FKR_11` FOREIGN KEY (`Id_Cli`) REFERENCES `Client` (`Id_Cli`),
ADD CONSTRAINT `FKR_12` FOREIGN KEY (`Id_TypeBot`) REFERENCES `TypeBottelage` (`Id_TypeBot`),
ADD CONSTRAINT `FKR_13` FOREIGN KEY (`Id_Ch`) REFERENCES `Champ` (`Id_Ch`),
ADD CONSTRAINT `FKR_14` FOREIGN KEY (`Id_Trans`) REFERENCES `Transport` (`Id_Trans`);

--
-- Contraintes pour la table `RecolteMachine`
--
ALTER TABLE `RecolteMachine`
ADD CONSTRAINT `FKId_Mach` FOREIGN KEY (`Id_Mach`) REFERENCES `Machine` (`Id_Mach`),
ADD CONSTRAINT `FKId_Rec` FOREIGN KEY (`Id_Rec`) REFERENCES `Recolte` (`Id_Rec`);

--
-- Contraintes pour la table `Tracteur`
--
ALTER TABLE `Tracteur`
ADD CONSTRAINT `FKR_4_FK` FOREIGN KEY (`Id_ModTract`) REFERENCES `ModeleTracteur` (`Id_ModTract`);

--
-- Contraintes pour la table `VitesseCulture`
--
ALTER TABLE `VitesseCulture`
ADD CONSTRAINT `FKId_Moi` FOREIGN KEY (`Id_Moi`) REFERENCES `Moissonneuse` (`Id_Moi`),
ADD CONSTRAINT `FKId_TypCult` FOREIGN KEY (`Id_TypCult`) REFERENCES `TypeCulture` (`Id_TypCult`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
