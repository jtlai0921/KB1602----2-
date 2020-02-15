package com.lietu.rtf.interpreterTests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.lietu.rtf.IRtfDocument;
import com.lietu.rtf.IRtfDocumentInfo;
import com.lietu.rtf.IRtfGroup;
import com.lietu.rtf.IRtfVisual;
import com.lietu.rtf.IRtfVisualBreak;
import com.lietu.rtf.IRtfVisualText;
import com.lietu.rtf.RtfPropertyKind;
import com.lietu.rtf.RtfSpec;
import com.lietu.rtf.RtfTextAlignment;
import com.lietu.rtf.RtfVisualBreakKind;
import com.lietu.rtf.RtfVisualKind;
import com.lietu.rtf.converter.text.RtfTextConverter;
import com.lietu.rtf.interpreter.RtfInterpreterListenerDocumentBuilder;
import com.lietu.rtf.interpreter.RtfInterpreterListenerLogger;
import com.lietu.rtf.model.RtfColor;
import com.lietu.rtf.parser.RtfParserListenerLogger;
import com.lietu.rtf.support.RtfInterpreterTool;
import com.lietu.rtf.support.RtfParserTool;

import junit.framework.Assert;
import junit.framework.TestCase;

public final class RtfInterpreterTest extends TestCase {
	public void setUp() {
	}

	public void testListSupport() throws Exception {
		RtfParserListenerLogger parserLogger = null;
		// parserLogger = new RtfParserListenerLogger();

		// string testCaseName = "RtfInterpreterTest_9";
		String testCaseName = "RtfInterpreterTest_10";
		IRtfGroup rtfStructure = RtfParserTool.Parse(
				GetTestResource(testCaseName + ".rtf"), parserLogger);

		RtfInterpreterListenerLogger interpreterLogger = null;
		// interpreterLogger = new RtfInterpreterListenerLogger();

		RtfTextConverter textConverter = new RtfTextConverter();

		RtfInterpreterTool.Interpret(rtfStructure, textConverter,
				interpreterLogger);

		String plainText = textConverter.getPlainText();
		System.out.println(plainText);
		// AssertEqualLines( "list support:",
		// new StreamReader( GetTestResource( testCaseName + ".txt" ),
		// RtfSpec.AnsiEncoding ),
		// new StringReader( plainText ) );
	} // ListSupportTest

	// ----------------------------------------------------------------------
	/*
	 * public void ImageFormatDecodingTest() { RtfParserListenerLogger
	 * parserLogger = null; //parserLogger = new RtfParserListenerLogger();
	 * 
	 * RtfInterpreterListenerLogger interpreterLogger = null;
	 * //interpreterLogger = new RtfInterpreterListenerLogger();
	 * 
	 * int[] imageResources = new int[] { 4, 5, 6, 7, 8 }; for ( int i = 0; i <
	 * imageResources.length; i++ ) { String testCaseName =
	 * BuildTestResourceName( "", imageResources[ i ], false, "rtf" ); IRtfGroup
	 * rtfStructure = RtfParserTool.Parse( GetTestResource( testCaseName ),
	 * parserLogger ); IRtfDocument rtfDoc = RtfInterpreterTool.BuildDoc(
	 * rtfStructure, interpreterLogger ); Assert. assertNotNull( rtfDoc ); for (
	 * IRtfVisual visual : rtfDoc.getVisualContent() ) { if ( visual.getKind()
	 * == RtfVisualKind.Image ) { IRtfVisualImage img = (IRtfVisualImage)visual;
	 * Assert. assertNotNull( img.getImageForDrawing() ); //Console.WriteLine(
	 * "image: " + img.ImageForDrawing ); } } } } // ImageFormatDecodingTest
	 */

	// ----------------------------------------------------------------------
	/*
	 * public void ImageTest() { RtfParserListenerLogger parserLogger = null;
	 * //parserLogger = new RtfParserListenerLogger(); IRtfGroup rtfStructure =
	 * RtfParserTool.Parse( GetTestResource( "RtfInterpreterTest_4.rtf" ),
	 * parserLogger ); Assert. assertNotNull( rtfStructure );
	 * 
	 * RtfInterpreterListenerLogger interpreterLogger = null;
	 * //interpreterLogger = new RtfInterpreterListenerLogger();
	 * 
	 * IRtfDocument rtfDoc = RtfInterpreterTool.BuildDoc( rtfStructure,
	 * interpreterLogger ); Assert. assertNotNull( rtfDoc );
	 * ArrayList<IRtfVisual> rtfVisuals = rtfDoc.getVisualContent();
	 * 
	 * Assert.assertEquals( RtfVisualKind.Image, rtfVisuals.get(4).getKind() );
	 * IRtfVisualImage img = (IRtfVisualImage)rtfVisuals.get(4);
	 * Assert.assertEquals( RtfVisualImageFormat.Jpg, img.getFormat() );
	 * Assert.assertEquals(100, img.getWidth()); Assert.assertEquals( 142,
	 * img.getHeight() ); Assert.assertEquals( 720, img.getDesiredWidth() );
	 * Assert.assertEquals( 1020, img.getDesiredHeight() ); } // ImageTest
	 */

	// ----------------------------------------------------------------------
	public void testTextAlignment() throws IOException, Exception {
		RtfParserListenerLogger parserLogger = null;
		// parserLogger = new RtfParserListenerLogger();
		IRtfGroup rtfStructure = RtfParserTool.Parse(
				GetTestResource("RtfInterpreterTest_3.rtf"), parserLogger);
		Assert.assertNotNull(rtfStructure);

		RtfInterpreterListenerLogger interpreterLogger = null;
		// interpreterLogger = new RtfInterpreterListenerLogger();

		IRtfDocument rtfDoc = RtfInterpreterTool.BuildDoc(rtfStructure,
				interpreterLogger);
		Assert.assertNotNull(rtfDoc);
		ArrayList<IRtfVisual> rtfVisuals = rtfDoc.getVisualContent();

		Assert.assertEquals(8, rtfVisuals.size());
		Assert.assertEquals(RtfVisualKind.Text, rtfVisuals.get(0).getKind());
		Assert.assertEquals("left aligned",
				((IRtfVisualText) rtfVisuals.get(0)).getText());
		Assert.assertEquals(RtfTextAlignment.Left, ((IRtfVisualText) rtfVisuals
				.get(0)).getFormat().getAlignment());
		Assert.assertEquals(RtfVisualKind.Text, rtfVisuals.get(2).getKind());
		Assert.assertEquals("centered", ((IRtfVisualText) rtfVisuals.get(2))
				.getText());
		Assert
				.assertEquals(RtfTextAlignment.Center,
						((IRtfVisualText) rtfVisuals.get(2)).getFormat()
								.getAlignment());
		Assert.assertEquals(RtfVisualKind.Text, rtfVisuals.get(4).getKind());
		Assert.assertEquals("right aligned", ((IRtfVisualText) rtfVisuals
				.get(4)).getText());
		Assert
				.assertEquals(RtfTextAlignment.Right,
						((IRtfVisualText) rtfVisuals.get(4)).getFormat()
								.getAlignment());
		Assert.assertEquals(RtfVisualKind.Text, rtfVisuals.get(6).getKind());
		Assert.assertEquals("block aligned", ((IRtfVisualText) rtfVisuals
				.get(6)).getText());
		Assert
				.assertEquals(RtfTextAlignment.Justify,
						((IRtfVisualText) rtfVisuals.get(6)).getFormat()
								.getAlignment());
	} // TextAlignmentTest

	// ----------------------------------------------------------------------

	public void DocumentBuilderTest() throws IOException, Exception {
		RtfParserListenerLogger parserLogger = null;
		// parserLogger = new RtfParserListenerLogger();
		IRtfGroup rtfStructure = RtfParserTool.Parse(
				GetTestResource("RtfInterpreterTest_1.rtf"), parserLogger);
		assertNotNull(rtfStructure);

		RtfInterpreterListenerLogger interpreterLogger = null;
		// interpreterLogger = new RtfInterpreterListenerLogger();

		RtfInterpreterListenerDocumentBuilder docBuilder = new RtfInterpreterListenerDocumentBuilder();

		RtfInterpreterTool.Interpret(rtfStructure, docBuilder,
				interpreterLogger);
		IRtfDocument doc = docBuilder.getDocument();
		assertNotNull(doc);

		Assert.assertEquals("TX_RTF32 14.0.520.501", doc.getGenerator());

		/*
		 * Assert.assertEquals( 3, doc.getFontTable().size());
		 * Assert.assertEquals( RtfFontKind.Swiss,
		 * doc.getFontTable().containsValue(value) ); Assert.assertEquals(
		 * RtfFontPitch.Variable, doc.FontTable[ 0 ].Pitch );
		 * Assert.assertEquals( 0, doc.FontTable[ 0 ].CharSet );
		 * Assert.assertEquals( 1252, doc.FontTable[ 0 ].CodePage );
		 * Assert.assertEquals( "Arial", doc.FontTable[ 0 ].Name );
		 * 
		 * Assert.assertEquals( RtfFontKind.Swiss, doc.FontTable[ 1 ].Kind );
		 * Assert.assertEquals( RtfFontPitch.Variable, doc.FontTable[ 1 ].Pitch
		 * ); Assert.assertEquals( 0, doc.FontTable[ 01 ].CharSet );
		 * Assert.assertEquals( 1252, doc.FontTable[ 1 ].CodePage );
		 * Assert.assertEquals( "Verdana", doc.FontTable[ 1 ].Name );
		 * 
		 * Assert.assertEquals( RtfFontKind.Roman, doc.FontTable[ 2 ].Kind );
		 * Assert.assertEquals( RtfFontPitch.Variable, doc.FontTable[ 2 ].Pitch
		 * ); Assert.assertEquals( 2, doc.FontTable[ 2 ].CharSet );
		 * Assert.assertEquals( 42, doc.FontTable[ 2 ].CodePage );
		 * Assert.assertEquals( "Symbol", doc.FontTable[ 2 ].Name );
		 * 
		 * Assert.AreSame( doc.getDefaultFont(), doc.FontTable[ 1 ] );
		 */

		Assert.assertEquals(4, doc.getColorTable().size());
		Assert.assertEquals(RtfColor.Black, doc.getColorTable().get(0));
		Assert.assertEquals(RtfColor.Black, doc.getColorTable().get(1));
		Assert.assertEquals(RtfColor.White, doc.getColorTable().get(2));
		Assert.assertEquals(new RtfColor(10, 20, 30), doc.getColorTable()
				.get(3));

		Assert.assertEquals(2, doc.getVisualContent().size());
		Assert.assertEquals(RtfVisualKind.Text, doc.getVisualContent().get(0)
				.getKind());
		Assert.assertEquals("Hellou RTF Wörld", ((IRtfVisualText) doc
				.getVisualContent().get(0)).getText());
		Assert.assertEquals("Verdana", ((IRtfVisualText) doc.getVisualContent()
				.get(0)).getFormat().getFont().getName());
		Assert.assertEquals(36,
				((IRtfVisualText) doc.getVisualContent().get(0)).getFormat()
						.getFontSize());
		Assert.assertEquals(RtfVisualKind.Break, doc.getVisualContent().get(1)
				.getKind());
		Assert.assertEquals(RtfVisualBreakKind.Paragraph,
				((IRtfVisualBreak) doc.getVisualContent().get(1))
						.getBreakKind());

		Assert.assertEquals(5, doc.getUserProperties().size());
		Assert
				.assertEquals("created", doc.getUserProperties().get(0)
						.getName());
		Assert.assertEquals(RtfPropertyKind.Date, doc.getUserProperties()
				.get(0).getPropertyKind());
		Assert.assertEquals("2008-05-23", doc.getUserProperties().get(0)
				.getStaticValue());
		assertNull(doc.getUserProperties().get(0).getLinkValue());
		Assert.assertEquals("a link", doc.getUserProperties().get(4)
				.getLinkValue());

		IRtfDocumentInfo info = doc.getDocumentInfo();
		Assert.assertEquals(2, info.getVersion());
		Assert.assertEquals(3, info.getRevision());
		Assert.assertEquals(1, info.getNumberOfPages());
		Assert.assertEquals(3, info.getNumberOfWords());
		Assert.assertEquals(16, info.getNumberOfCharacters());
		Assert.assertEquals(314, info.getId());
		Assert.assertEquals(17, info.getEditingTimeInMinutes());
		Assert.assertEquals("Not really important", info.getTitle());
		Assert.assertEquals("RTF parsing", info.getSubject());
		Assert.assertEquals("John Doe", info.getAuthor());
		Assert.assertEquals("John Doe's boss", info.getManager());
		Assert.assertEquals("Itenso GmbH", info.getCompany());
		Assert.assertEquals("Foo Bar", info.getOperator());
		Assert.assertEquals("Development", info.getCategory());
		Assert.assertEquals("RTF, Parser, Interpreter", info.getKeywords());
		Assert.assertEquals("a testing document", info.getComment());
		Assert.assertEquals("with more commentary", info.getDocumentComment());
		Assert.assertEquals("http://wwww.nowhere.com/foo/bar", info
				.getHyperLinkbase());
		Assert
				.assertEquals(Time("2008.05.23-17:55:12"), info
						.getCreationTime());
		Assert
				.assertEquals(Time("2008.05.23-18:01:00"), info
						.getRevisionTime());
		Assert.assertEquals(Time("2008.05.23-17:59:00"), info.getPrintTime());
		Assert.assertEquals(Time("2008.05.23-18:00:00"), info.getBackupTime());
	} // DocumentBuilderTest

	// ----------------------------------------------------------------------
	private Date Time(String time) {
		SimpleDateFormat frm = new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss");
		try {
			return frm.parse(time);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	} // Time

	// ----------------------------------------------------------------------

	public void UnicodeSupportTest() throws IOException, Exception {
		RtfParserListenerLogger parserLogger = null;
		// parserLogger = new RtfParserListenerLogger();

		IRtfGroup rtfStructure = RtfParserTool.Parse(
				GetTestResource("RtfInterpreterTest_2.rtf"), parserLogger);
		assertNotNull(rtfStructure);

		RtfInterpreterListenerLogger interpreterLogger = null;
		// interpreterLogger = new RtfInterpreterListenerLogger();

		RtfTextConverter textConverter = new RtfTextConverter();

		RtfInterpreterTool.Interpret(rtfStructure, textConverter,
				interpreterLogger);

		String plainText = textConverter.getPlainText();
		// AssertEqualLines( "unicode support:",
		// new StreamReader( GetTestResource( "RtfInterpreterTest_2.txt" ),
		// RtfSpec.AnsiEncoding ),
		// new StringReader( plainText ) );
	} // UnicodeSupportTest

	// ----------------------------------------------------------------------

	public void ExtractPlainTextTest() throws IOException, Exception {
		RtfParserListenerLogger parserLogger = null;
		// parserLogger = new RtfParserListenerLogger();
		IRtfGroup rtfStructure = RtfParserTool.Parse(
				GetTestResource("RtfInterpreterTest_0.rtf"), parserLogger);
		assertNotNull(rtfStructure);

		RtfInterpreterListenerLogger interpreterLogger = null;
		// interpreterLogger = new RtfInterpreterListenerLogger();

		RtfTextConverter textConverter = new RtfTextConverter();

		RtfInterpreterTool.Interpret(rtfStructure, textConverter,
				interpreterLogger);

		String plainText = textConverter.getPlainText();
		assertEquals("Hello RTF World\r\n", plainText);
	} // ExtractPlainTextTest

	// ----------------------------------------------------------------------
	protected void DoTest(String kind, String testRes, String testCaseName)
			throws Exception {
		RtfParserListenerLogger parserLogger = null;
		// parserLogger = new RtfParserListenerLogger();

		RtfInterpreterListenerLogger interpreterLogger = null;
		// interpreterLogger = new RtfInterpreterListenerLogger();

		RtfTextConverter textConverter = new RtfTextConverter();

		RtfInterpreterListenerDocumentBuilder docBuilder = new RtfInterpreterListenerDocumentBuilder();

		RtfInterpreterTool.Interpret(
				RtfParserTool.Parse(testRes, parserLogger), interpreterLogger,
				textConverter, docBuilder);

		String plainText = textConverter.getPlainText();
		assertEquals(true, plainText.length() > 0);
		// Assert.IsFalse( string.IsNullOrEmpty( plainText ) );
		assertNotNull(docBuilder.getDocument());

		String testName = testCaseName.substring(0, testCaseName.length() - 4);
		// string unicode = "バージョンアップ注文書（銀行振込）";
		Charset enc = null;
		if (testName == "RtfInterpreterTest_12") {
			;
		} else if (testName == "RtfInterpreterTest_13") {
			;
		} else if (testName == "RtfInterpreterTest_14") {
			enc = Charset.forName("UTF-8");// Encoding.Unicode;
		} else {
			enc = RtfSpec.AnsiEncoding;
		}

		String referenceResName = testName + ".txt";
		/*
		 * if ( "RtfInterpreterTest_14".Equals( testName ) ) { using ( Stream s
		 * = new FileStream( @"w:\tmp\RtfInterpreterTest_14.txt",
		 * FileMode.Create ) ) { using ( TextWriter w = new StreamWriter( s, enc
		 * ) ) { w.Write( plainText ); } } } //
		 */
		// AssertEqualLines( "document recognition: " + testCaseName + ":",
		// new StreamReader( getRtfFile( referenceResName ), enc ),
		// new StringReader( plainText ) );
	} // DoTest

	public static String GetTestResource(String fileName) throws IOException {
        //System.out.println(fileName);
	
		InputStream file = (new RtfInterpreterTest()).getClass().getResourceAsStream("/resources/"+fileName);
		
		BufferedReader in;
		in = new BufferedReader(new InputStreamReader(file,"GBK"));
		StringBuilder str = new StringBuilder();
		String line;
		while ((line = in.readLine()) != null) {
			str.append(line);
		}
		return str.toString();
	}

} // class RtfInterpreterTest

