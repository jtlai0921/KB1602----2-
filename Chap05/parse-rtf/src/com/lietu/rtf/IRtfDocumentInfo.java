package com.lietu.rtf;

import java.util.Date;

	public interface IRtfDocumentInfo
	{

		// ----------------------------------------------------------------------
		int getId(); 

		// ----------------------------------------------------------------------
		int getVersion();

		// ----------------------------------------------------------------------
		int getRevision();

		// ----------------------------------------------------------------------
		String getTitle();

		// ----------------------------------------------------------------------
		String getSubject();

		// ----------------------------------------------------------------------
		String getAuthor();

		// ----------------------------------------------------------------------
		String getManager();

		// ----------------------------------------------------------------------
		String getCompany();

		// ----------------------------------------------------------------------
		String getOperator();

		// ----------------------------------------------------------------------
		String getCategory();

		// ----------------------------------------------------------------------
		String getKeywords();

		// ----------------------------------------------------------------------
		String getComment();

		// ----------------------------------------------------------------------
		String getDocumentComment();

		// ----------------------------------------------------------------------
		String getHyperLinkbase();

		// ----------------------------------------------------------------------
		Date getCreationTime();

		// ----------------------------------------------------------------------
		Date getRevisionTime();

		// ----------------------------------------------------------------------
		Date getPrintTime();

		// ----------------------------------------------------------------------
		Date getBackupTime();

		// ----------------------------------------------------------------------
		int getNumberOfPages();

		// ----------------------------------------------------------------------
		int getNumberOfWords();

		// ----------------------------------------------------------------------
		int getNumberOfCharacters();

		// ----------------------------------------------------------------------
		int getEditingTimeInMinutes();

	} // interface IRtfDocumentInfo

