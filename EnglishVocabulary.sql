USE [master]
GO
/****** Object:  Database [EnglishVocabulary]    Script Date: 21/09/2015 10:41:13 CH ******/
CREATE DATABASE [EnglishVocabulary]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'EnglishVocabulary', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL11.SERVER_LUANDT\MSSQL\DATA\EnglishVocabulary.mdf' , SIZE = 5120KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'EnglishVocabulary_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL11.SERVER_LUANDT\MSSQL\DATA\EnglishVocabulary_log.ldf' , SIZE = 2048KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [EnglishVocabulary] SET COMPATIBILITY_LEVEL = 110
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [EnglishVocabulary].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [EnglishVocabulary] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [EnglishVocabulary] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [EnglishVocabulary] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [EnglishVocabulary] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [EnglishVocabulary] SET ARITHABORT OFF 
GO
ALTER DATABASE [EnglishVocabulary] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [EnglishVocabulary] SET AUTO_CREATE_STATISTICS ON 
GO
ALTER DATABASE [EnglishVocabulary] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [EnglishVocabulary] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [EnglishVocabulary] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [EnglishVocabulary] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [EnglishVocabulary] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [EnglishVocabulary] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [EnglishVocabulary] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [EnglishVocabulary] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [EnglishVocabulary] SET  DISABLE_BROKER 
GO
ALTER DATABASE [EnglishVocabulary] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [EnglishVocabulary] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [EnglishVocabulary] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [EnglishVocabulary] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [EnglishVocabulary] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [EnglishVocabulary] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [EnglishVocabulary] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [EnglishVocabulary] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [EnglishVocabulary] SET  MULTI_USER 
GO
ALTER DATABASE [EnglishVocabulary] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [EnglishVocabulary] SET DB_CHAINING OFF 
GO
ALTER DATABASE [EnglishVocabulary] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [EnglishVocabulary] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
USE [EnglishVocabulary]
GO
/****** Object:  User [luandt]    Script Date: 21/09/2015 10:41:13 CH ******/
CREATE USER [luandt] WITHOUT LOGIN WITH DEFAULT_SCHEMA=[dbo]
GO
/****** Object:  Table [dbo].[administrator]    Script Date: 21/09/2015 10:41:14 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[administrator](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[username] [nvarchar](50) NOT NULL,
	[password] [nvarchar](50) NOT NULL,
	[fullname] [nvarchar](50) NOT NULL,
	[access] [tinyint] NOT NULL,
	[status] [tinyint] NOT NULL,
 CONSTRAINT [PK_Administrator] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[categories]    Script Date: 21/09/2015 10:41:14 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[categories](
	[cate_id] [bigint] IDENTITY(1,1) NOT NULL,
	[id_clien] [bigint] NOT NULL,
	[name] [nvarchar](100) NOT NULL,
	[user_id] [bigint] NOT NULL,
 CONSTRAINT [PK_categories] PRIMARY KEY CLUSTERED 
(
	[cate_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[feedback]    Script Date: 21/09/2015 10:41:14 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[feedback](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[content] [nvarchar](max) NOT NULL,
	[date] [date] NOT NULL,
 CONSTRAINT [PK_feedback] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[gcm_message]    Script Date: 21/09/2015 10:41:14 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[gcm_message](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[title] [nvarchar](max) NOT NULL,
	[content] [nvarchar](max) NOT NULL,
	[url_image] [nvarchar](max) NOT NULL,
	[link] [nvarchar](max) NOT NULL,
	[date_create] [datetime] NOT NULL,
	[sender] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_message] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[gcm_registration]    Script Date: 21/09/2015 10:41:14 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[gcm_registration](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[reg_id] [nvarchar](max) NOT NULL,
	[date_created] [date] NOT NULL,
 CONSTRAINT [PK_gcm_registration] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[users]    Script Date: 21/09/2015 10:41:14 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[users](
	[user_id] [bigint] IDENTITY(1,1) NOT NULL,
	[username] [nvarchar](30) NOT NULL,
	[password] [nvarchar](50) NOT NULL,
	[fullname] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_users] PRIMARY KEY CLUSTERED 
(
	[user_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[vocabularies]    Script Date: 21/09/2015 10:41:14 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[vocabularies](
	[voca_id] [bigint] IDENTITY(1,1) NOT NULL,
	[id_clien] [bigint] NOT NULL,
	[cate_id] [bigint] NOT NULL,
	[english] [nvarchar](100) NOT NULL,
	[vietnamese] [nvarchar](100) NOT NULL,
	[user_id] [bigint] NOT NULL,
 CONSTRAINT [PK_vocabularies] PRIMARY KEY CLUSTERED 
(
	[voca_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
ALTER TABLE [dbo].[administrator] ADD  CONSTRAINT [DF_Administrator_access]  DEFAULT ((0)) FOR [access]
GO
ALTER TABLE [dbo].[administrator] ADD  CONSTRAINT [DF_Administrator_status]  DEFAULT ((0)) FOR [status]
GO
ALTER TABLE [dbo].[feedback] ADD  CONSTRAINT [DF_feedback_date]  DEFAULT (getdate()) FOR [date]
GO
ALTER TABLE [dbo].[gcm_message] ADD  CONSTRAINT [DF_message_date_create]  DEFAULT (getdate()) FOR [date_create]
GO
ALTER TABLE [dbo].[gcm_registration] ADD  CONSTRAINT [DF_gcm_registration_date_created]  DEFAULT (getdate()) FOR [date_created]
GO
USE [master]
GO
ALTER DATABASE [EnglishVocabulary] SET  READ_WRITE 
GO
