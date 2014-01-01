package com.grahammueller.sidewalk.xpath;

public class XPathToken {
  public enum TokenType { CHILD, ANCESTOR, CHILD_ATTRIBUTE, ANCESTOR_ATTRIBUTE }

  /**
   * Constructor for a token
   * 
   * @param type Token type
   * @param value Token value
   */
  public XPathToken(TokenType type, String value) {
    _type = type;
    _value = value;
  }

  /**
   * Getter for the type of the token
   * 
   * @return This token's type
   */
  public TokenType getType() {
    return _type;
  }

  /**
   * Getter for the value of the token
   * 
   * @return This token's value
   */
  public String getValue() {
    return _value;
  }  

  public String toString() {
    return String.format("%s %s", String.valueOf(_type), _value);
  }

  private final TokenType _type;
  private final String _value;
}
