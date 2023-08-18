-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 18, 2023 at 04:09 AM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.4.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `spotify_reminder`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(4) NOT NULL,
  `kd_admin` varchar(6) DEFAULT NULL,
  `username` varchar(30) DEFAULT NULL,
  `password` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `kd_admin`, `username`, `password`) VALUES
(1, 'AD0001', 'admin', '21232f297a57a5a743894a0e4a801fc3');

-- --------------------------------------------------------

--
-- Table structure for table `paket`
--

CREATE TABLE `paket` (
  `id` int(4) NOT NULL,
  `kd_paket` varchar(6) DEFAULT NULL,
  `nama` varchar(30) DEFAULT NULL,
  `durasi` int(4) DEFAULT NULL,
  `harga` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `paket`
--

INSERT INTO `paket` (`id`, `kd_paket`, `nama`, `durasi`, `harga`) VALUES
(1, 'PK0001', 'spotify_1', 1, '5000'),
(2, 'PK0002', 'spotify_3', 3, '15000'),
(3, 'PK0003', 'spotify_6', 6, '20000'),
(4, 'PK0004', 'spotify_12', 12, '40000');

-- --------------------------------------------------------

--
-- Table structure for table `pelanggan`
--

CREATE TABLE `pelanggan` (
  `id` int(4) NOT NULL,
  `kd_pelanggan` varchar(6) DEFAULT NULL,
  `nama` varchar(30) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pelanggan`
--

INSERT INTO `pelanggan` (`id`, `kd_pelanggan`, `nama`, `email`) VALUES
(1, 'PL0001', 'Adi Puspita', 'adi@gmail.com'),
(2, 'PL0002', 'Ginger Harbert', 'gharbert1@feedburner.com'),
(3, 'PL0003', 'Erin Lorryman', 'elorryman2@oaic.gov.au'),
(4, 'PL0004', 'Jeremiah Mairs', 'jmairs3@furl.com'),
(5, 'PL0005', 'Legra Rehme', 'lrehme4@foxnews.com'),
(6, 'PL0006', 'Libbie Corps', 'lcorps5@zimbio.com'),
(7, 'PL0007', 'Ailsun Crush', 'acrush6@dot.gov'),
(8, 'PL0008', 'Fannie Lawman', 'flawman7@shop-pro.jp'),
(9, 'PL0009', 'Gert Gallardo', 'ggallardo8@goodreads.com'),
(10, 'PL0010', 'Loutitia Zanni', 'lzanni9@spiegel.de'),
(11, 'PL0011', 'Janella Grundle', 'jgrundlea@myspace.com'),
(12, 'PL0012', 'Lewiss Caudray', 'lcaudrayb@xrea.com'),
(13, 'PL0013', 'Abbot Phayre', 'aphayrec@miibeian.gov.cn'),
(14, 'PL0014', 'Staci Roseaman', 'sroseamand@dot.gov'),
(15, 'PL0015', 'Muriel Dregan', 'mdregane@mtv.com'),
(16, 'PL0016', 'Giana Mum', 'gmumf@google.de'),
(17, 'PL0017', 'Templeton Yeoman', 'tyeomang@state.tx.us'),
(18, 'PL0018', 'Erinna Satyford', 'esatyfordh@linkedin.com'),
(19, 'PL0019', 'Dewitt Davsley', 'ddavsleyi@reference.com'),
(20, 'PL0020', 'Kippy Innwood', 'kinnwoodj@sakura.ne.jp'),
(21, 'PL0021', 'Briant Ewdale', 'bewdalek@baidu.com'),
(22, 'PL0022', 'Ettore Aish', 'eaishl@aboutads.info'),
(23, 'PL0023', 'Zaccaria Cavee', 'zcaveem@cam.ac.uk'),
(24, 'PL0024', 'Izzy Dacre', 'idacren@cloudflare.com'),
(25, 'PL0025', 'Sela Caldeiro', 'scaldeiroo@constantcontact.com'),
(26, 'PL0026', 'Pandora Goodoune', 'pgoodounep@acquirethisname.com'),
(27, 'PL0027', 'Alon Norvell', 'anorvellq@liveinternet.ru'),
(28, 'PL0028', 'Evie Peskett', 'epeskettr@trellian.com'),
(29, 'PL0029', 'Caria Riccio', 'criccios@unblog.fr'),
(30, 'PL0030', 'Valina Cossentine', 'vcossentinet@bloglines.com'),
(31, 'PL0031', 'Harri Keene', 'hkeeneu@netlog.com'),
(32, 'PL0032', 'Gibby Carratt', 'gcarrattv@businesswire.com'),
(33, 'PL0033', 'Aymer Jouanet', 'ajouanetw@berkeley.edu'),
(34, 'PL0034', 'Nona Wolfers', 'nwolfersx@furl.net'),
(35, 'PL0035', 'Cesare Pinke', 'cpinkey@goodreads.com'),
(36, 'PL0036', 'Courtney Gerrelts', 'cgerreltsz@cisco.com'),
(37, 'PL0037', 'Rubi Chatto', 'rchatto10@webmd.com'),
(38, 'PL0038', 'Margi Isles', 'misles11@ucsd.edu'),
(39, 'PL0039', 'Sibylle Templman', 'stemplman12@aboutads.info'),
(40, 'PL0040', 'Corabelle Peerman', 'cpeerman13@guardian.co.uk'),
(41, 'PL0041', 'Sabrina Blondin', 'sblondin14@bloglovin.com'),
(42, 'PL0042', 'Eden Powdrell', 'epowdrell15@harvard.edu'),
(43, 'PL0043', 'Donn Ethelston', 'dethelston16@mapquest.com'),
(44, 'PL0044', 'Hilario Birrell', 'hbirrell17@alexa.com'),
(45, 'PL0045', 'Gerladina Sockell', 'gsockell18@g.co'),
(46, 'PL0046', 'Peterus Hanhard', 'phanhard19@xing.com'),
(47, 'PL0047', 'Hilarius Battison', 'hbattison1a@blogspot.com'),
(48, 'PL0048', 'Lil Quarterman', 'lquarterman1b@surveymonkey.com'),
(49, 'PL0049', 'Cori Barnardo', 'cbarnardo1c@hostgator.com'),
(51, 'PL0050', 'Test', 'test@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `sumber_order`
--

CREATE TABLE `sumber_order` (
  `id` int(4) NOT NULL,
  `kd_sumber` varchar(6) DEFAULT NULL,
  `nama` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `sumber_order`
--

INSERT INTO `sumber_order` (`id`, `kd_sumber`, `nama`) VALUES
(1, 'SO0001', 'Tokopedia'),
(2, 'SO0002', 'Shopee'),
(3, 'SO0003', 'TikTok');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `id` int(11) NOT NULL,
  `kd_transaksi` varchar(6) NOT NULL,
  `kd_pelanggan` varchar(6) NOT NULL,
  `kd_paket` varchar(6) NOT NULL,
  `kd_admin` varchar(6) NOT NULL,
  `kd_sumber` varchar(6) NOT NULL,
  `tgl_aktif` date NOT NULL,
  `tgl_exp` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`id`, `kd_transaksi`, `kd_pelanggan`, `kd_paket`, `kd_admin`, `kd_sumber`, `tgl_aktif`, `tgl_exp`) VALUES
(1, 'TR0001', 'PL0001', 'PK0004', 'AD0001', 'SO0002', '2023-01-01', '2024-01-01'),
(2, 'TR0002', 'PL0002', 'PK0002', 'AD0001', 'SO0001', '2023-08-15', '2023-11-15'),
(3, 'TR0003', 'PL0003', 'PK0002', 'AD0001', 'SO0003', '2023-02-01', '2023-05-01'),
(4, 'TR0004', 'PL0008', 'PK0004', 'AD0001', 'SO0003', '2023-02-20', '2024-02-20'),
(6, 'TR0006', 'PL0006', 'PK0004', 'AD0001', 'SO0002', '2023-04-21', '2024-04-21'),
(7, 'TR0007', 'PL0005', 'PK0004', 'AD0001', 'SO0001', '2023-06-01', '2024-06-01'),
(8, 'TR0008', 'PL0004', 'PK0002', 'AD0001', 'SO0002', '2023-06-22', '2023-09-22'),
(9, 'TR0009', 'PL0009', 'PK0003', 'AD0001', 'SO0003', '2023-07-01', '2024-01-01'),
(10, 'TR0010', 'PL0010', 'PK0002', 'AD0001', 'SO0001', '2023-07-21', '2023-09-21'),
(11, 'TR0011', 'PL0001', 'PK0001', 'AD0001', 'SO0001', '2023-08-15', '2023-09-15');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `kd_admin` (`kd_admin`);

--
-- Indexes for table `paket`
--
ALTER TABLE `paket`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `kd_paket` (`kd_paket`);

--
-- Indexes for table `pelanggan`
--
ALTER TABLE `pelanggan`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `kd_pelanggan` (`kd_pelanggan`);

--
-- Indexes for table `sumber_order`
--
ALTER TABLE `sumber_order`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `kd_sumber` (`kd_sumber`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `kd_transaksi` (`kd_transaksi`),
  ADD KEY `kd_pelanggan` (`kd_pelanggan`),
  ADD KEY `kd_paket` (`kd_paket`),
  ADD KEY `kd_admin` (`kd_admin`),
  ADD KEY `kd_sumber` (`kd_sumber`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `paket`
--
ALTER TABLE `paket`
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `pelanggan`
--
ALTER TABLE `pelanggan`
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;

--
-- AUTO_INCREMENT for table `sumber_order`
--
ALTER TABLE `sumber_order`
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `transaksi_ibfk_1` FOREIGN KEY (`kd_pelanggan`) REFERENCES `pelanggan` (`kd_pelanggan`),
  ADD CONSTRAINT `transaksi_ibfk_2` FOREIGN KEY (`kd_paket`) REFERENCES `paket` (`kd_paket`),
  ADD CONSTRAINT `transaksi_ibfk_3` FOREIGN KEY (`kd_admin`) REFERENCES `admin` (`kd_admin`),
  ADD CONSTRAINT `transaksi_ibfk_4` FOREIGN KEY (`kd_sumber`) REFERENCES `sumber_order` (`kd_sumber`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
