package com.example.customvalidation.entity;

import lombok.Data;

import java.util.Date;

@Data

public class Header {
	public SenderIdentifier SenderIdentifier;
	public ReceiverIdentifier ReceiverIdentifier;
	public String TransactionIdentifier;
	public Date Timestamp;
	public Version Version;
}
