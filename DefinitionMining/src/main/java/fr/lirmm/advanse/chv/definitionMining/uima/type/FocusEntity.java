

/* First created by JCasGen Wed Jun 15 10:55:37 CEST 2016 */
package fr.lirmm.advanse.chv.definitionMining.uima.type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Wed Jun 15 11:44:47 CEST 2016
 * XML source: /home/monordi/releases/mining-definitions/DefinitionMining/src/main/resources/desc/type/DefinitionMining.xml
 * @generated */
public class FocusEntity extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(FocusEntity.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected FocusEntity() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public FocusEntity(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public FocusEntity(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public FocusEntity(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
  //*--------------*
  //* Feature: Text

  /** getter for Text - gets 
   * @generated
   * @return value of the feature 
   */
  public String getText() {
    if (FocusEntity_Type.featOkTst && ((FocusEntity_Type)jcasType).casFeat_Text == null)
      jcasType.jcas.throwFeatMissing("Text", "fr.lirmm.advanse.chv.definitionMining.uima.type.FocusEntity");
    return jcasType.ll_cas.ll_getStringValue(addr, ((FocusEntity_Type)jcasType).casFeatCode_Text);}
    
  /** setter for Text - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setText(String v) {
    if (FocusEntity_Type.featOkTst && ((FocusEntity_Type)jcasType).casFeat_Text == null)
      jcasType.jcas.throwFeatMissing("Text", "fr.lirmm.advanse.chv.definitionMining.uima.type.FocusEntity");
    jcasType.ll_cas.ll_setStringValue(addr, ((FocusEntity_Type)jcasType).casFeatCode_Text, v);}    
   
    
  //*--------------*
  //* Feature: DefinitionText

  /** getter for DefinitionText - gets 
   * @generated
   * @return value of the feature 
   */
  public String getDefinitionText() {
    if (FocusEntity_Type.featOkTst && ((FocusEntity_Type)jcasType).casFeat_DefinitionText == null)
      jcasType.jcas.throwFeatMissing("DefinitionText", "fr.lirmm.advanse.chv.definitionMining.uima.type.FocusEntity");
    return jcasType.ll_cas.ll_getStringValue(addr, ((FocusEntity_Type)jcasType).casFeatCode_DefinitionText);}
    
  /** setter for DefinitionText - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setDefinitionText(String v) {
    if (FocusEntity_Type.featOkTst && ((FocusEntity_Type)jcasType).casFeat_DefinitionText == null)
      jcasType.jcas.throwFeatMissing("DefinitionText", "fr.lirmm.advanse.chv.definitionMining.uima.type.FocusEntity");
    jcasType.ll_cas.ll_setStringValue(addr, ((FocusEntity_Type)jcasType).casFeatCode_DefinitionText, v);}    
  }

    