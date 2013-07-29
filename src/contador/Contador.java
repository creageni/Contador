
package contador;

import java.util.logging.Level;
import java.util.logging.Logger;

//Implementamos la Interfaz Runnable que nos pedira que agregemos los metodos no implementados,
//en este caso el metodo run (Linea 161)
public class Contador extends javax.swing.JFrame implements Runnable {

    //Bandera que indica si el hilo esta corriendo o no
    boolean bandera;
    //Objeto hilo de clase Thread, con el cual podremos manejar el flujo de ejecucion alterno
    Thread hilo;   
    //Contador que se incrementara cada segundo
    int con=0;
   
    public Contador() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        tiempo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("iniciar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Detener");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        tiempo.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        tiempo.setText("00");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Ejercicio XIII");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(tiempo)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(tiempo)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     //Metodo que se llama al realizar un Click sobre el boton iniciar
     //Preguntamos si el hilo es igual a nulo o si esta vivo
     //Para que sea igual a nulo es necesario que hilo se declare pero no se instancie
     //Si el hilo esta vivo, el mertodo ilAlive() me retorna un true, y con el operador NOT !
     //Se convertiria en False y no entraria segun ese cirterio al IF   
        
     if(hilo==null ||!hilo.isAlive())   {
        //En caso de que el hilo este nulo o muerto, instanciamos el hilo y lo lanzamos
        hilo= new Thread(this);
        hilo.start(); //Lanza el hilo y hace que se ejecute el metodo run (Linea 161)
     }else{
        //En caso del hilo no estar nulo y no estar muerto, reiniciamos el contador
        //Este caso se llamaria despues de ´presionar el boton iniciar y presionar el boton de pausa, y volver a presionar el 
        //boton de iniciar
        con=0;
     }
     
     
     
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      //Configura la bandera en false para que se dentenga el hilo
      bandera=false;
    }//GEN-LAST:event_jButton2ActionPerformed

    //Metodo que tiene la implementacion del contador 
    //Visibilidad: public, Retorno: void (no retorno), Nombre: contador, Argumentos: no argumentos
    public void contador(){
        //Ciclo while que realiza las interaciones hasta que bandera sea igual a false
        while(bandera){
            //Control de errores requerido para dormir el hilo (.sleep)
            try {
                //Duerme o inactiva el hilo por un segundo (1000 ms)
                Thread.sleep(1000);
                //Incrementa el contador en una unidad
                con++;
                //Colocamos el contador en el Label tiempo
                tiempo.setText(""+con);  
                
            } 
            //Captura de la Excepcion InterruptedException, esta se lanza cuando se interrumpe el hilo mientras este esta inactivo
            catch (InterruptedException ex) {
                Logger.getLogger(Contador.class.getName()).log(Level.SEVERE, null, ex);
            }
                 
        }
        
    }
    
    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Contador().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel tiempo;
    // End of variables declaration//GEN-END:variables
    
    //Metodo run, se invoca al lanzar el hilo
    //Al finalizar todas las sentencias del metodo run, el hilo pasa a estar en un estado de muerte
    
    @Override
    public void run() {
        //Configuramos la bandera en true
        bandera=true;
        //Llamamos al metodo contador (Linea 120)
        contador();
    }
}
