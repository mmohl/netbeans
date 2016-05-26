-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 26 Mei 2016 pada 07.23
-- Versi Server: 10.1.9-MariaDB
-- PHP Version: 5.5.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pvl_tb_kel`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `detail_pesanan`
--

CREATE TABLE `detail_pesanan` (
  `id` int(10) UNSIGNED NOT NULL,
  `id_pesanan` int(10) UNSIGNED NOT NULL,
  `uang_muka` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `kategori`
--

CREATE TABLE `kategori` (
  `id` int(10) UNSIGNED NOT NULL,
  `kategori` varchar(20) NOT NULL,
  `harga` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `kategori`
--

INSERT INTO `kategori` (`id`, `kategori`, `harga`) VALUES
(1, 'Baru', 50000),
(2, 'Sedang', 40000),
(3, 'Baik', 30000),
(4, 'Penyayang', 80000),
(5, 'Seksi', 90000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `pasangan`
--

CREATE TABLE `pasangan` (
  `id` int(10) UNSIGNED NOT NULL,
  `id_pegawai` int(10) UNSIGNED NOT NULL,
  `id_kategori` varchar(50) NOT NULL,
  `harga` smallint(5) UNSIGNED NOT NULL,
  `status` char(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `pasangan`
--

INSERT INTO `pasangan` (`id`, `id_pegawai`, `id_kategori`, `harga`, `status`) VALUES
(1, 1, '2', 7000, '1'),
(2, 3, '1.2.3', 1000, '1');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pegawai`
--

CREATE TABLE `pegawai` (
  `id` int(10) UNSIGNED NOT NULL,
  `nama` varchar(50) NOT NULL,
  `jenis_kelamin` char(1) NOT NULL,
  `tanggal_lahir` date NOT NULL,
  `ktp` char(16) NOT NULL,
  `no_handphone` varchar(12) NOT NULL,
  `gambar` varchar(50) DEFAULT NULL,
  `pekerjaan` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `pegawai`
--

INSERT INTO `pegawai` (`id`, `nama`, `jenis_kelamin`, `tanggal_lahir`, `ktp`, `no_handphone`, `gambar`, `pekerjaan`) VALUES
(1, 'Maulana', 'p', '2006-02-22', '1111111111111111', '08997226285', NULL, '1'),
(3, 'Modi', 'p', '2016-05-25', '1111111122222222', '08997226285', NULL, 'Item 1'),
(4, 'Desi', 'l', '2016-05-14', '9909090900909090', '08997226285', NULL, '2');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pesanan`
--

CREATE TABLE `pesanan` (
  `id` int(10) UNSIGNED NOT NULL,
  `id_pasangan` int(10) UNSIGNED NOT NULL,
  `tanggal` date NOT NULL,
  `lama` tinyint(3) UNSIGNED NOT NULL,
  `penyewa` varchar(50) NOT NULL,
  `ktp` char(16) NOT NULL,
  `no_handphone` varchar(12) NOT NULL,
  `status` char(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `pesanan`
--

INSERT INTO `pesanan` (`id`, `id_pasangan`, `tanggal`, `lama`, `penyewa`, `ktp`, `no_handphone`, `status`) VALUES
(1, 1, '2016-05-26', 2, 'Maulana', '1111119999112345', '08997226285', '1'),
(2, 1, '2016-05-12', 5, 'Beben', '9090909090909090', '08997226285', '0');

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE `user` (
  `id` int(10) UNSIGNED NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `status` char(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `status`) VALUES
(1, 'Maulana', '123', '0'),
(2, 'Kosasi', 'papah', '1'),
(3, 'Mahmud', 'krisye', '0'),
(4, 'Gintoki', 'gintama', '0');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `detail_pesanan`
--
ALTER TABLE `detail_pesanan`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_pesanan` (`id_pesanan`);

--
-- Indexes for table `kategori`
--
ALTER TABLE `kategori`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pasangan`
--
ALTER TABLE `pasangan`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_kategori` (`id_kategori`),
  ADD KEY `fk_pegawai` (`id_pegawai`);

--
-- Indexes for table `pegawai`
--
ALTER TABLE `pegawai`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `u_ktp` (`ktp`);

--
-- Indexes for table `pesanan`
--
ALTER TABLE `pesanan`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `ktp` (`ktp`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `u_username` (`username`),
  ADD KEY `username` (`username`),
  ADD KEY `username_2` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `detail_pesanan`
--
ALTER TABLE `detail_pesanan`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `kategori`
--
ALTER TABLE `kategori`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `pasangan`
--
ALTER TABLE `pasangan`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `pegawai`
--
ALTER TABLE `pegawai`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `pesanan`
--
ALTER TABLE `pesanan`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `detail_pesanan`
--
ALTER TABLE `detail_pesanan`
  ADD CONSTRAINT `detail_pesanan_ibfk_1` FOREIGN KEY (`id_pesanan`) REFERENCES `pesanan` (`id`);

--
-- Ketidakleluasaan untuk tabel `pasangan`
--
ALTER TABLE `pasangan`
  ADD CONSTRAINT `fk_pegawai` FOREIGN KEY (`id_pegawai`) REFERENCES `pegawai` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
