package com.grahammueller.sidewalk.xpath;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.zachtaylor.jnodalxml.XmlNode;

import com.grahammueller.sidewalk.xpath.XPathToken.TokenType;

public class XPathSelector {
  /**
   * Constructor for an XPath Query
   * 
   * @param query The XPath Query
   * @param searchNode The node to which the query will be relative
   */
  public XPathSelector(String query, XmlNode searchNode) {
    _query = query;

    // The context root is the parent of the
    // node we're searching from. If the node
    // provided is already a root, we wrap it.
    XmlNode parent = searchNode.getParent();
    if (parent == null) {
      searchNode.setParent(new XmlNode("root"));
    }

    _contextRootNode = searchNode.getParent();
  }

  public List<Object> execute() {
    tokenize();

    List<Object> matches = new ArrayList<Object>();
    matches.add(_contextRootNode);

    XPathToken token = null;
    while ((token = _tokens.poll()) != null) {
      TokenType tokenType = token.getType();
      String tokenValue = token.getValue();
      List<Object> tokenMatches = new ArrayList<Object>();

      for (Object match : matches) {
        XmlNode matchNode = (XmlNode) match;

        if (tokenType == TokenType.CHILD || tokenType == TokenType.CHILD_ATTRIBUTE) {
          if (tokenType == TokenType.CHILD) {
            tokenMatches.addAll(matchNode.getChildren(tokenValue));
          }
          else if (tokenType == TokenType.CHILD_ATTRIBUTE) {
            if (matchNode.hasAttribute(tokenValue)) {
              tokenMatches.add(matchNode.getAttribute(tokenValue));
            }
          }
        }
        else {
          Queue<XmlNode> childrenToCheck = new LinkedList<XmlNode>();
          childrenToCheck.add(matchNode);
          XmlNode ancestor = null;
          while ((ancestor = childrenToCheck.poll()) != null) {
            childrenToCheck.addAll(ancestor.getAllChildren());

            if (tokenType == TokenType.ANCESTOR) {
              tokenMatches.addAll(ancestor.getChildren(tokenValue));
            }
            else if (tokenType == TokenType.ANCESTOR_ATTRIBUTE) {
              if (ancestor.hasAttribute(tokenValue)) {
                tokenMatches.add(ancestor.getAttribute(tokenValue));
              }
            }
          }
        }
      }

      if (tokenMatches.isEmpty()) {
        return tokenMatches;
      }

      matches = tokenMatches;
    }

    return matches;
  }

  private void tokenize() {
    String queryTokenized = _query.replace("//", ">a ")
                                  .replace("/", ">c ")
                                  .replace(">a @", ">@a ")
                                  .replace(">c @", ">@c ");

    if (queryTokenized.startsWith(">")) {
      // Drop leading >, drop empty leading token
      queryTokenized = queryTokenized.substring(1);
    }
    else {
      // Else, prepend with child, since just "xxx"
      // indicates search direct child, not ancestor
      queryTokenized = "c " + queryTokenized;
    }

    String[] tokenStrings = queryTokenized.split(">");

    _tokens = new LinkedList<XPathToken>();
    for (String tokenString : tokenStrings) {
      XPathToken token = null;

      switch (tokenString.charAt(0)) {
        case 'a': token = new XPathToken(TokenType.ANCESTOR, tokenString.substring(2)); break;
        case 'c': token = new XPathToken(TokenType.CHILD, tokenString.substring(2)); break;
        case '@':
            char attrType = tokenString.charAt(1);
            if (attrType == 'a') {
              token = new XPathToken(TokenType.ANCESTOR_ATTRIBUTE, tokenString.substring(3)); break;
            }
            else if (attrType == 'c') {
              token = new XPathToken(TokenType.CHILD_ATTRIBUTE, tokenString.substring(3)); break;
            }
      }

      _tokens.add(token);
    }
  }

  public String toString() {
    return String.format("%s on %s", _query, _contextRootNode);
  }

  private XmlNode _contextRootNode;
  private String _query;
  public Queue<XPathToken> _tokens;
}
