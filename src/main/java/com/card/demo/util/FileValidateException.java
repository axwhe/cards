package com.card.demo.util;

public class FileValidateException extends Exception
{
      /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FileValidateException() {}

      //Constructor that accepts a message
      public FileValidateException(String message)
      {
         super(message);
      }
 }