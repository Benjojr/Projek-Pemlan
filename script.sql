/****** Object:  Database [ProjectPemlan]    Script Date: 11/06/2025 13:45:33 ******/
CREATE DATABASE [ProjectPemlan]
GO
USE [ProjectPemlan]
GO
ALTER DATABASE [ProjectPemlan] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [ProjectPemlan] SET MULTI_USER 
GO
CREATE LOGIN [user_pemlan] WITH PASSWORD = 'MBankKuDeveloper_123';
GO
CREATE USER [user_pemlan] FOR LOGIN [user_pemlan] WITH DEFAULT_SCHEMA = [dbo];
GO
EXEC sys.sp_addrolemember @rolename = N'db_owner', @membername = N'user_pemlan';
GO
CREATE TABLE [dbo].[Nasabah](
	[id] [varchar](10) NOT NULL,
	[nama] [varchar](200) NULL,
	[NIK] [varchar](16) NULL,
	[noRek] [varchar](20) NULL,
	[PIN] [varchar](6) NULL,
	[noHP] [varchar](15) NULL,
	[email] [varchar](200) NULL,
	[saldo] [decimal](15, 2) NULL,
	[Bank] [varchar](10) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER AUTHORIZATION ON [dbo].[Nasabah] TO  SCHEMA OWNER 
GO
/****** Object:  Table [dbo].[Pengguna]    Script Date: 11/06/2025 13:45:44 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Pengguna](
	[id] [varchar](10) NOT NULL,
	[username] [varchar](50) NOT NULL,
	[password] [varchar](50) NOT NULL,
	[NBID] [varchar](10) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER AUTHORIZATION ON [dbo].[Pengguna] TO  SCHEMA OWNER 
GO
/****** Object:  Table [dbo].[RiwayatTopUp]    Script Date: 11/06/2025 13:45:44 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[RiwayatTopUp](
	[id] [varchar](10) NOT NULL,
	[noRekPengirim] [varchar](20) NOT NULL,
	[noHpPenerima] [varchar](20) NOT NULL,
	[jenisEWallet] [varchar](50) NOT NULL,
	[nominal] [decimal](15, 2) NOT NULL,
	[tanggal] [date] NOT NULL,
	[waktu] [time](7) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER AUTHORIZATION ON [dbo].[RiwayatTopUp] TO  SCHEMA OWNER 
GO
/****** Object:  Table [dbo].[RiwayatTransfer]    Script Date: 11/06/2025 13:45:44 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[RiwayatTransfer](
	[id] [varchar](10) NOT NULL,
	[noRekPengirim] [varchar](20) NOT NULL,
	[noRekPenerima] [varchar](20) NOT NULL,
	[bankPengirim] [varchar](50) NOT NULL,
	[bankPenerima] [varchar](50) NOT NULL,
	[nominal] [decimal](15, 2) NOT NULL,
	[tanggal] [date] NOT NULL,
	[waktu] [time](7) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER AUTHORIZATION ON [dbo].[RiwayatTransfer] TO  SCHEMA OWNER 
GO
INSERT [dbo].[Nasabah] ([id], [nama], [NIK], [noRek], [PIN], [noHP], [email], [saldo], [Bank]) VALUES (N'NB001', N'Ben Jason Maruli Ritonga', N'1271072608060002', N'0012345678', N'123456', N'085814209573', N'benjason2608@gmail.com', CAST(977000000.00 AS Decimal(15, 2)), N'BCA')
INSERT [dbo].[Nasabah] ([id], [nama], [NIK], [noRek], [PIN], [noHP], [email], [saldo], [Bank]) VALUES (N'NB002', N'Rina Ayu Lestari', N'3175081204000001', N'2134567890', N'654321', N'081234567891', N'rina.ayu01@email.com', CAST(2500000.00 AS Decimal(15, 2)), N'BCA')
INSERT [dbo].[Nasabah] ([id], [nama], [NIK], [noRek], [PIN], [noHP], [email], [saldo], [Bank]) VALUES (N'NB003', N'Dedi Firmansyah', N'3201090101800002', N'3245678901', N'112233', N'082345678912', N'dedi.firman@email.com', CAST(20875000.00 AS Decimal(15, 2)), N'Mandiri')
INSERT [dbo].[Nasabah] ([id], [nama], [NIK], [noRek], [PIN], [noHP], [email], [saldo], [Bank]) VALUES (N'NB004', N'Siti Nurhaliza', N'3276012303950003', N'4356789012', N'445566', N'083456789123', N'siti.nurhaliza@email.com', CAST(1160000.00 AS Decimal(15, 2)), N'Mandiri')
INSERT [dbo].[Nasabah] ([id], [nama], [NIK], [noRek], [PIN], [noHP], [email], [saldo], [Bank]) VALUES (N'NB005', N'Arif Budi Santoso', N'3301021506920004', N'5467890123', N'778899', N'084567891234', N'arif.santoso@email.com', CAST(8200000.00 AS Decimal(15, 2)), N'BNI')
INSERT [dbo].[Nasabah] ([id], [nama], [NIK], [noRek], [PIN], [noHP], [email], [saldo], [Bank]) VALUES (N'NB006', N'Maria Kristina Simanjuntak', N'1271050405970005', N'6578901234', N'990011', N'085678912345', N'maria.kristina@email.com', CAST(20987654.00 AS Decimal(15, 2)), N'Permata')
INSERT [dbo].[Nasabah] ([id], [nama], [NIK], [noRek], [PIN], [noHP], [email], [saldo], [Bank]) VALUES (N'NB007', N'Agus Dwi Hartono', N'3374091101900007', N'7689012345', N'121212', N'081112223344', N'agus.dwi@email.com', CAST(110000.00 AS Decimal(15, 2)), N'BSI')
INSERT [dbo].[Nasabah] ([id], [nama], [NIK], [noRek], [PIN], [noHP], [email], [saldo], [Bank]) VALUES (N'NB008', N'Linda Mawar Sari', N'3276022205990008', N'8790123456', N'343434', N'082223334455', N'linda.mawar@email.com', CAST(765000.00 AS Decimal(15, 2)), N'BSI')
INSERT [dbo].[Nasabah] ([id], [nama], [NIK], [noRek], [PIN], [noHP], [email], [saldo], [Bank]) VALUES (N'NB009', N'Fajar Prasetyo', N'3301050607910009', N'9801234567', N'565656', N'083334445566', N'fajar.prasetyo@email.com', CAST(345000.00 AS Decimal(15, 2)), N'BRI')
INSERT [dbo].[Nasabah] ([id], [nama], [NIK], [noRek], [PIN], [noHP], [email], [saldo], [Bank]) VALUES (N'NB010', N'Nina Kartika Putri', N'3201101802010010', N'0912345678', N'787878', N'084445556677', N'nina.kartika@email.com', CAST(1120000.00 AS Decimal(15, 2)), N'BNI')
INSERT [dbo].[Nasabah] ([id], [nama], [NIK], [noRek], [PIN], [noHP], [email], [saldo], [Bank]) VALUES (N'NB011', N'Pak Arwan', N'3201090808000001', N'9988776655', N'901234', N'081212345678', N'arwan@ub.ac.id', CAST(2010000000.00 AS Decimal(15, 2)), N'BCA')
INSERT [dbo].[Nasabah] ([id], [nama], [NIK], [noRek], [PIN], [noHP], [email], [saldo], [Bank]) VALUES (N'NB012', N'Joshua Nathanael Purba', N'3276012303950562', N'1853510435', N'234567', N'081211360972', N'anjaymabar@gmail.com', CAST(0.00 AS Decimal(15, 2)), N'BNI')
GO
INSERT [dbo].[Pengguna] ([id], [username], [password], [NBID]) VALUES (N'P001', N'benjason26', N'okemantap', N'NB001')
INSERT [dbo].[Pengguna] ([id], [username], [password], [NBID]) VALUES (N'P002', N'rinaayu234', N'anjaymabar', N'NB002')
INSERT [dbo].[Pengguna] ([id], [username], [password], [NBID]) VALUES (N'P003', N'pakarwan', N'123456', N'NB011')
GO
INSERT [dbo].[RiwayatTopUp] ([id], [noRekPengirim], [noHpPenerima], [jenisEWallet], [nominal], [tanggal], [waktu]) VALUES (N'TP001', N'0012345678', N'085814209573', N'Dana', CAST(1000000.00 AS Decimal(15, 2)), CAST(N'2025-06-09' AS Date), CAST(N'17:40:36.8300000' AS Time))
INSERT [dbo].[RiwayatTopUp] ([id], [noRekPengirim], [noHpPenerima], [jenisEWallet], [nominal], [tanggal], [waktu]) VALUES (N'TP002', N'0012345678', N'085814209573', N'Dana', CAST(1000000.00 AS Decimal(15, 2)), CAST(N'2025-06-09' AS Date), CAST(N'17:57:54.2800000' AS Time))
GO
INSERT [dbo].[RiwayatTransfer] ([id], [noRekPengirim], [noRekPenerima], [bankPengirim], [bankPenerima], [nominal], [tanggal], [waktu]) VALUES (N'TF001', N'0012345678', N'6578901234', N'BCA', N'PERMATA', CAST(10000000.00 AS Decimal(15, 2)), CAST(N'2025-06-09' AS Date), CAST(N'17:17:28.8633333' AS Time))
INSERT [dbo].[RiwayatTransfer] ([id], [noRekPengirim], [noRekPenerima], [bankPengirim], [bankPenerima], [nominal], [tanggal], [waktu]) VALUES (N'TF002', N'0012345678', N'2134567890', N'BCA', N'BCA', CAST(1000000.00 AS Decimal(15, 2)), CAST(N'2025-06-09' AS Date), CAST(N'23:48:20.2766667' AS Time))
GO
ALTER TABLE [dbo].[Pengguna]  WITH CHECK ADD  CONSTRAINT [FK_Pengguna_Nasabah] FOREIGN KEY([NBID])
REFERENCES [dbo].[Nasabah] ([id])
GO
ALTER TABLE [dbo].[Pengguna] CHECK CONSTRAINT [FK_Pengguna_Nasabah]
GO
/****** Object:  Trigger [dbo].[trg_generate_id_Nasabah]    Script Date: 11/06/2025 13:45:45 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TRIGGER [dbo].[trg_generate_id_Nasabah]
ON [dbo].[Nasabah]
INSTEAD OF INSERT
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @maxId INT;

    SELECT @maxId = ISNULL(MAX(CAST(SUBSTRING(id, 3, LEN(id)) AS INT)), 0)
    FROM Nasabah;

    ;WITH NumberedRows AS (
        SELECT 
            ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS RowNum,
            nama, NIK, noRek, PIN, noHP, email, saldo, bank
        FROM inserted
    )
    INSERT INTO Nasabah (id, nama, NIK, noRek, PIN, noHP, email, saldo, bank)
    SELECT 
        'NB' + RIGHT('000' + CAST(@maxId + RowNum AS VARCHAR), 3),
        nama, NIK, noRek, PIN, noHP, email, saldo, bank
    FROM NumberedRows;
END
GO
ALTER TABLE [dbo].[Nasabah] ENABLE TRIGGER [trg_generate_id_Nasabah]
GO
/****** Object:  Trigger [dbo].[trg_BuatID_Pengguna]    Script Date: 11/06/2025 13:45:46 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TRIGGER [dbo].[trg_BuatID_Pengguna]
ON [dbo].[Pengguna]
INSTEAD OF INSERT
AS
BEGIN
    DECLARE @angkaTerakhir INT;

    SELECT @angkaTerakhir = 
        ISNULL(MAX(CAST(SUBSTRING(id, 2, LEN(id)) AS INT)), 0)
    FROM Pengguna;

    -- Tabel sementara untuk menyimpan hasil insert dengan ID unik
    DECLARE @dataBaru TABLE (id VARCHAR(10), username VARCHAR(100), password VARCHAR(100), NPID VARCHAR(20));

    INSERT INTO @dataBaru (id, username, password, NPID)
    SELECT 
        'P' + RIGHT('000' + CAST(ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) + @angkaTerakhir AS VARCHAR), 3),
        username,
        password,
        NBID
    FROM inserted;

    -- Masukkan ke tabel asli
    INSERT INTO Pengguna (id, username, password, NBID)
    SELECT id, username, password, NPID FROM @dataBaru;
END;
GO
ALTER TABLE [dbo].[Pengguna] ENABLE TRIGGER [trg_BuatID_Pengguna]
GO
/****** Object:  Trigger [dbo].[trg_AutoGenerateID_TopUp]    Script Date: 11/06/2025 13:45:47 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TRIGGER [dbo].[trg_AutoGenerateID_TopUp]
ON [dbo].[RiwayatTopUp]
INSTEAD OF INSERT
AS
BEGIN
    DECLARE @nextId VARCHAR(10)

    SELECT TOP 1 @nextId = RIGHT('000' + CAST(CAST(SUBSTRING(id, 3, LEN(id)) AS INT) + 1 AS VARCHAR), 3)
    FROM RiwayatTopUp
    WHERE ISNUMERIC(SUBSTRING(id, 3, LEN(id))) = 1
    ORDER BY id DESC

    IF @nextId IS NULL SET @nextId = '001'

    INSERT INTO RiwayatTopUp (id, noRekPengirim, noHpPenerima, jenisEWallet, nominal, tanggal, waktu)
    SELECT 
        'TP' + @nextId,
        noRekPengirim,
        noHpPenerima,
        jenisEWallet,
        nominal,
        tanggal,
        waktu
    FROM inserted
END
GO
ALTER TABLE [dbo].[RiwayatTopUp] ENABLE TRIGGER [trg_AutoGenerateID_TopUp]
GO
/****** Object:  Trigger [dbo].[trg_AutoGenerateID]    Script Date: 11/06/2025 13:45:47 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TRIGGER [dbo].[trg_AutoGenerateID]
ON [dbo].[RiwayatTransfer]
INSTEAD OF INSERT
AS
BEGIN
    DECLARE @nextId VARCHAR(10)

    SELECT TOP 1 @nextId = RIGHT('000' + CAST(CAST(SUBSTRING(id, 3, LEN(id)) AS INT) + 1 AS VARCHAR), 3)
    FROM RiwayatTransfer
    WHERE ISNUMERIC(SUBSTRING(id, 3, LEN(id))) = 1
    ORDER BY id DESC

    IF @nextId IS NULL SET @nextId = '001'

    INSERT INTO RiwayatTransfer (id, noRekPengirim, noRekPenerima, bankPengirim, bankPenerima, nominal, tanggal, waktu)
    SELECT 
        'TF' + @nextId,
        noRekPengirim,
        noRekPenerima,
        bankPengirim,
        bankPenerima,
        nominal,
        tanggal,
        waktu
    FROM inserted
END
GO
ALTER TABLE [dbo].[RiwayatTransfer] ENABLE TRIGGER [trg_AutoGenerateID]
GO
ALTER DATABASE [ProjectPemlan] SET  READ_WRITE 
GO
