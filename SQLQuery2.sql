CREATE DATABASE loja;
GO

USE loja;
GO

-- Criação da tabela Pessoa
CREATE TABLE Pessoa (
    ID_Pessoa INT PRIMARY KEY IDENTITY(1,1),
    Nome NVARCHAR(100) NOT NULL,
    Logradouro NVARCHAR(200) NOT NULL,
    Cidade NVARCHAR(100) NOT NULL,
    Estado NVARCHAR(100) NOT NULL,
    Telefone NVARCHAR(15),
    Email NVARCHAR(100)
);
GO

-- Criação da tabela PessoaFisica
CREATE TABLE PessoaFisica (
    ID_PessoaFisica INT PRIMARY KEY IDENTITY(1,1),
    ID_Pessoa INT NOT NULL,
    CPF NVARCHAR(14) NOT NULL UNIQUE,
    FOREIGN KEY (ID_Pessoa) REFERENCES Pessoa(ID_Pessoa)
);
GO

-- Criação da tabela PessoaJuridica
CREATE TABLE PessoaJuridica (
    ID_PessoaJuridica INT PRIMARY KEY IDENTITY(1,1),
    ID_Pessoa INT NOT NULL,
    CNPJ NVARCHAR(18) NOT NULL UNIQUE,
    FOREIGN KEY (ID_Pessoa) REFERENCES Pessoa(ID_Pessoa)
);
GO
