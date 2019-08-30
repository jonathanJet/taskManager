USE [master]
GO
/****** Object:  Database [db_proj]    Script Date: 30/08/2019 21:31:16 ******/
CREATE DATABASE [db_proj]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'db_productos', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.SDPJ1\MSSQL\DATA\db_productos.mdf' , SIZE = 20480KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'db_productos_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.SDPJ1\MSSQL\DATA\db_productos_log.ldf' , SIZE = 73728KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [db_proj] SET COMPATIBILITY_LEVEL = 140
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [db_proj].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [db_proj] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [db_proj] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [db_proj] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [db_proj] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [db_proj] SET ARITHABORT OFF 
GO
ALTER DATABASE [db_proj] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [db_proj] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [db_proj] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [db_proj] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [db_proj] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [db_proj] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [db_proj] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [db_proj] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [db_proj] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [db_proj] SET  DISABLE_BROKER 
GO
ALTER DATABASE [db_proj] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [db_proj] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [db_proj] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [db_proj] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [db_proj] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [db_proj] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [db_proj] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [db_proj] SET RECOVERY FULL 
GO
ALTER DATABASE [db_proj] SET  MULTI_USER 
GO
ALTER DATABASE [db_proj] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [db_proj] SET DB_CHAINING OFF 
GO
ALTER DATABASE [db_proj] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [db_proj] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [db_proj] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'db_proj', N'ON'
GO
ALTER DATABASE [db_proj] SET QUERY_STORE = OFF
GO
USE [db_proj]
GO
/****** Object:  User [kfigueroa]    Script Date: 30/08/2019 21:31:17 ******/
CREATE USER [kfigueroa] FOR LOGIN [kfigueroa] WITH DEFAULT_SCHEMA=[kfigueroa]
GO
/****** Object:  DatabaseRole [MSmerge_PAL_role]    Script Date: 30/08/2019 21:31:17 ******/
CREATE ROLE [MSmerge_PAL_role]
GO
/****** Object:  DatabaseRole [MSmerge_D8703CD63BC94420942328093EF6CE17]    Script Date: 30/08/2019 21:31:17 ******/
CREATE ROLE [MSmerge_D8703CD63BC94420942328093EF6CE17]
GO
ALTER ROLE [db_owner] ADD MEMBER [kfigueroa]
GO
ALTER ROLE [db_datareader] ADD MEMBER [kfigueroa]
GO
ALTER ROLE [db_datawriter] ADD MEMBER [kfigueroa]
GO
ALTER ROLE [MSmerge_PAL_role] ADD MEMBER [MSmerge_D8703CD63BC94420942328093EF6CE17]
GO
/****** Object:  Schema [kfigueroa]    Script Date: 30/08/2019 21:31:17 ******/
CREATE SCHEMA [kfigueroa]
GO
/****** Object:  Schema [MSmerge_PAL_role]    Script Date: 30/08/2019 21:31:17 ******/
CREATE SCHEMA [MSmerge_PAL_role]
GO
/****** Object:  Table [dbo].[tbl_estado]    Script Date: 30/08/2019 21:31:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_estado](
	[id_estado] [int] NOT NULL,
	[nombre_estado] [text] NOT NULL,
 CONSTRAINT [PK_tbl_estado-tarea] PRIMARY KEY CLUSTERED 
(
	[id_estado] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_roles]    Script Date: 30/08/2019 21:31:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_roles](
	[id_rol] [int] NOT NULL,
	[nombre_rol] [text] NOT NULL,
	[activo] [int] NOT NULL,
 CONSTRAINT [PK_tbl_roles] PRIMARY KEY CLUSTERED 
(
	[id_rol] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_tarea]    Script Date: 30/08/2019 21:31:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_tarea](
	[id_tarea] [int] NOT NULL,
	[nombre] [varchar](150) NOT NULL,
	[descripcion] [text] NOT NULL,
	[tiempo_real] [int] NOT NULL,
	[tiempo_estimado] [int] NOT NULL,
	[id_estado] [int] NOT NULL,
	[id_usuario] [int] NOT NULL,
 CONSTRAINT [PK_tbl_tarea] PRIMARY KEY CLUSTERED 
(
	[id_tarea] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_usuario]    Script Date: 30/08/2019 21:31:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_usuario](
	[id_usuario] [int] NOT NULL,
	[nombre] [varchar](150) NOT NULL,
	[usuario] [varchar](50) NOT NULL,
	[contrasenia] [varchar](50) NOT NULL,
	[activo] [int] NOT NULL,
	[rowguid] [uniqueidentifier] ROWGUIDCOL  NOT NULL,
 CONSTRAINT [PK_tbl_usuario] PRIMARY KEY CLUSTERED 
(
	[id_usuario] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_usuario_rol]    Script Date: 30/08/2019 21:31:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_usuario_rol](
	[id] [int] NOT NULL,
	[id_usuario] [int] NOT NULL,
	[id_rol] [int] NULL,
	[rowguid] [uniqueidentifier] ROWGUIDCOL  NOT NULL,
 CONSTRAINT [PK_tbl_usuario_rol] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Index [MSmerge_index_510624862]    Script Date: 30/08/2019 21:31:17 ******/
CREATE UNIQUE NONCLUSTERED INDEX [MSmerge_index_510624862] ON [dbo].[tbl_usuario]
(
	[rowguid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
/****** Object:  Index [MSmerge_index_542624976]    Script Date: 30/08/2019 21:31:17 ******/
CREATE UNIQUE NONCLUSTERED INDEX [MSmerge_index_542624976] ON [dbo].[tbl_usuario_rol]
(
	[rowguid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
ALTER TABLE [dbo].[tbl_usuario] ADD  CONSTRAINT [DF_tbl_usuario_activo]  DEFAULT ((1)) FOR [activo]
GO
ALTER TABLE [dbo].[tbl_usuario] ADD  CONSTRAINT [MSmerge_df_rowguid_E391593BFA1A4B5097B48AF33EEF57B2]  DEFAULT (newsequentialid()) FOR [rowguid]
GO
ALTER TABLE [dbo].[tbl_usuario_rol] ADD  CONSTRAINT [MSmerge_df_rowguid_3314E490B3DA48D5B088FEAC08F442B4]  DEFAULT (newsequentialid()) FOR [rowguid]
GO
ALTER TABLE [dbo].[tbl_tarea]  WITH CHECK ADD  CONSTRAINT [FK_tbl_tarea_tbl_estado] FOREIGN KEY([id_tarea])
REFERENCES [dbo].[tbl_estado] ([id_estado])
GO
ALTER TABLE [dbo].[tbl_tarea] CHECK CONSTRAINT [FK_tbl_tarea_tbl_estado]
GO
ALTER TABLE [dbo].[tbl_tarea]  WITH CHECK ADD  CONSTRAINT [FK_tbl_tarea_tbl_usuario] FOREIGN KEY([id_usuario])
REFERENCES [dbo].[tbl_usuario] ([id_usuario])
GO
ALTER TABLE [dbo].[tbl_tarea] CHECK CONSTRAINT [FK_tbl_tarea_tbl_usuario]
GO
ALTER TABLE [dbo].[tbl_usuario_rol]  WITH CHECK ADD  CONSTRAINT [FK_tbl_usuario_rol_tbl_roles] FOREIGN KEY([id_rol])
REFERENCES [dbo].[tbl_roles] ([id_rol])
GO
ALTER TABLE [dbo].[tbl_usuario_rol] CHECK CONSTRAINT [FK_tbl_usuario_rol_tbl_roles]
GO
ALTER TABLE [dbo].[tbl_usuario_rol]  WITH CHECK ADD  CONSTRAINT [FK_tbl_usuario_rol_tbl_usuario] FOREIGN KEY([id_usuario])
REFERENCES [dbo].[tbl_usuario] ([id_usuario])
GO
ALTER TABLE [dbo].[tbl_usuario_rol] CHECK CONSTRAINT [FK_tbl_usuario_rol_tbl_usuario]
GO
USE [master]
GO
ALTER DATABASE [db_proj] SET  READ_WRITE 
GO
