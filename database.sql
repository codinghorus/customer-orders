CREATE TABLE [dbo].[Products] (
    [ProductId]   INT           IDENTITY (1, 1) NOT NULL,
    [ProductName] NVARCHAR (50) NOT NULL,
    [Importance]  INT           NOT NULL,
    PRIMARY KEY CLUSTERED ([ProductId] ASC)
);

CREATE TABLE [dbo].[Customers] (
    [CustomerId] INT            IDENTITY (1, 1) NOT NULL,
    [Priority]   INT            NOT NULL,
    [Address]    NVARCHAR (256) NULL,
    PRIMARY KEY CLUSTERED ([CustomerId] ASC)
);

CREATE TABLE [dbo].[Orders] (
    [OrderId]    INT IDENTITY (1, 1) NOT NULL,
    [CustomerId] INT NOT NULL,
    [ProductId]  INT NOT NULL,
    PRIMARY KEY CLUSTERED ([OrderId] ASC),
    CONSTRAINT [FK_Orders_Customers] FOREIGN KEY ([CustomerId]) REFERENCES [dbo].[Customers] ([CustomerId]),
    CONSTRAINT [FK_Orders_Products] FOREIGN KEY ([ProductId]) REFERENCES [dbo].[Products] ([ProductId])
);
