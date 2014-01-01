package com.grahammueller.sidewalk;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import org.zachtaylor.jnodalxml.XmlNode;
import org.zachtaylor.jnodalxml.XmlParser;

import com.grahammueller.sidewalk.xpath.XPathSelector;

public class XPathFileScanner {
  public static void main(String[] args) {
    if (args.length < 2) {
      printUsage();
      System.exit(0);
    }

    String filePath = args[0];
    String xPathQuery = args[1];

    List<XmlNode> roots = loadXmlRoot(filePath);

    performQueryOnRoots(roots, xPathQuery);
  }

  private static List<XmlNode> loadXmlRoot(String filePath) {
    File xmlFile = new File(filePath);
    List<XmlNode> xmlRoots = null;

    try {
      xmlRoots = XmlParser.parse(xmlFile);
    }
    catch (FileNotFoundException fnfe) {
      System.out.println("Failure: " + fnfe.getLocalizedMessage());
      System.exit(1);
    }

    return xmlRoots;
  }

  private static void performQueryOnRoots(List<XmlNode> roots, String xPathQuery) {
    for (XmlNode root : roots) {
      XPathSelector query = new XPathSelector(xPathQuery, root);

      for (Object match : query.execute()) {
        System.out.println(match);
      }
    }

  }

  private static void printUsage() {
    System.out.println("Sidewalk XPathFileScanner Usage:\n" +
                       "xpathfilescanner file-path xpath-query");
  }
}
