/***
  Copyright (c) 2013 CommonsWare, LLC
  Licensed under the Apache License, Version 2.0 (the "License"); you may not
  use this file except in compliance with the License. You may obtain a copy
  of the License at http://www.apache.org/licenses/LICENSE-2.0. Unless required
  by applicable law or agreed to in writing, software distributed under the
  License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
  OF ANY KIND, either express or implied. See the License for the specific
  language governing permissions and limitations under the License.
  
  From _The Busy Coder's Guide to Android Development_
    http://commonsware.com/Android
 */

package com.commonsware.android.preso.simpler;

import android.app.Activity;
import android.app.Presentation;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;

import com.commonsware.cwac.preso.PresentationHelper;


/**
 * Modificação para projeto de TCC. 
 * @author hermivaldo
 *
 */
public class MainActivity extends Activity implements
    PresentationHelper.Listener {
  Presentation preso=null;
  PresentationHelper helper=null;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_main);
    helper=new PresentationHelper(this, this);
  }
  /*
   * Caso os métodos onResume() e onPause()
   * não forem reescritos o conteúdo da segunda
   * tela não aparecerá.
   */

  @Override
  public void onResume() {
    super.onResume();
    helper.onResume();
  }

  @Override
  public void onPause() {
    helper.onPause();
    super.onPause();
  }

  @Override
  public void clearPreso(boolean showInline) {
    if (preso != null) {
      preso.dismiss();
      preso=null;
    }
  }

  @Override
  public void showPreso(Display display) {
    preso=new SimplerPresentation(this, display);
    /*
     * Removido para verificar se o layout poderia ser inflado
     * em qualquer parte do sistema
     */
    preso.show();
  }
  
  
  /*
   * A função de visualização pode ser ativada normalmente,
   * pode ser que algo que demore a carregar deva ser 
   * carregado em uma tela, contudo essa linha comprova que
   * a segunda tela pode sim ser ativada com uma determinada
   * ação, nem sempre existe a necessidade de algo sendo exibido.
   */
  public void ativaSegunda(View view){
	  this.preso.setContentView(R.layout.exemplo);
	  Button temp = (Button) this.preso.findViewById(R.id.btn_click);
	  /*
	   * O conteúdo pode ser alterado da classe externa, basta mapear 
	   * o ID do elemento desse segundo layout para a tela atual.
	   */
	  temp.setText("Olá de uma nova forma");
	  this.preso.show();
  }
  
  /*
   * Considerando que a segunda tela não será touch os objetos não podem
   * ser manipulados com o click direto na segunda tela logo: faz-se a 
   * necessidade de alterar os itens da segunda tela com base nos componentes
   * da primeira tela.
   */

  /*
   * Uma única classePresentation pode ser inflada com o conteúdo nescessário,
   * fazendo a necessidade apenas de criar os respectivos layouts e eventos
   * para depois serem inseridos.
   */
  private class SimplerPresentation extends Presentation {
    SimplerPresentation(Context ctxt, Display display) {
      super(ctxt, display);
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
    	
    	setContentView(R.layout.exemplo);
    }
  }
}
