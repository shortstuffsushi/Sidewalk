package com.grahammueller.sidewalk.xpath;

public class XPathToken {
  public enum TokenLevel { CHILD, DESCENDANT }
  public enum TokenType { NODE, ATTRIBUTE }

  /**
   * Constructor for a token
   * 
   * @param level Token level
   * @param type Token type
   * @param value Token value
   */
  public XPathToken(TokenLevel level, TokenType type, String value) {
    _level = level;
    _type = type;
    _value = value;
  }

  /**
   * Getter for the level of the token
   * 
   * @return This token's level
   */
  public TokenLevel getLevel() {
    return _level;
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
    return String.format("%s %s %s", String.valueOf(_level), String.valueOf(_type), _value);
  }

  private final TokenLevel _level;
  private final TokenType _type;
  private final String _value;
}
