
package com.POSTransaction.view;

import com.POSGlobal.controller.clsGlobalVarClass;
import com.POSGlobal.controller.clsUtility;
import com.POSGlobal.view.frmSearchFormDialog;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class frmCloseProductionOrder extends javax.swing.JFrame {

    private String sql;
    private clsUtility objUtility;
    
    public frmCloseProductionOrder() {
       
        initComponents();
        objUtility=new clsUtility();
        lblUserCode.setText(clsGlobalVarClass.gUserCode);
        lblPosName.setText(clsGlobalVarClass.gPOSName);
        lblDate.setText(clsGlobalVarClass.gPOSDateToDisplay);
        lblModuleName.setText(clsGlobalVarClass.gSelectedModule);

        //fillComboBox();
    }
    
    /*private void fillComboBox()
    {
        try
        {   
            sql="select count(*) from tblreasonmaster where strUnsettleBill='Y'";
            ResultSet rs=clsGlobalVarClass.dbMysql.executeResultSet(sql);
            rs.next();
            int cnt=rs.getInt(1);
            int cntReasonCode=0;
            if(cnt>0)
            {
                    ReasonCode=new String[cnt];
                    sql="select strReasonCode,strReasonName from tblreasonmaster where strUnsettleBill='Y'";
                    rs=clsGlobalVarClass.dbMysql.executeResultSet(sql);
                    while(rs.next())
                    {
                        ReasonCode[cntReasonCode]=rs.getString(1);
                        cmbReason.addItem(rs.getString(2));
                        cntReasonCode++;
                    }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }*/
    
    private void funResetField()
    {
        txtProductionOrderNo.setText("");
    }    

    private void funUnsettleBill()
    {
        try
        {
            sql="update tblproductionhd set strClose='Y' where strProductionCode='"+txtProductionOrderNo.getText()+"'";
            if(clsGlobalVarClass.dbMysql.execute(sql)>0)
            {
                JOptionPane.showMessageDialog(this,"Order Closed Successfully");
                funResetField();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        panelHeader = new javax.swing.JPanel();
        lblProductName = new javax.swing.JLabel();
        lblModuleName = new javax.swing.JLabel();
        lblformName = new javax.swing.JLabel();
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 32767));
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        lblPosName = new javax.swing.JLabel();
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        lblUserCode = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        lblHOSign = new javax.swing.JLabel();
        panelMainForm = new JPanel() {  
            public void paintComponent(Graphics g) {  
                Image img = Toolkit.getDefaultToolkit().getImage(  
                    getClass().getResource("/com/POSTransaction/images/imgBackgroundImage.png"));  
                g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);  
            }  
        };  ;
        panelFormBody = new javax.swing.JPanel();
        lbFormName = new javax.swing.JLabel();
        txtProductionOrderNo = new javax.swing.JTextField();
        lblBillNo = new javax.swing.JLabel();
        btnCancel = new javax.swing.JButton();
        btnCloseProdOrder = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowClosed(java.awt.event.WindowEvent evt)
            {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt)
            {
                formWindowClosing(evt);
            }
        });

        panelHeader.setBackground(new java.awt.Color(69, 164, 238));
        panelHeader.setLayout(new javax.swing.BoxLayout(panelHeader, javax.swing.BoxLayout.LINE_AXIS));

        lblProductName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblProductName.setForeground(new java.awt.Color(255, 255, 255));
        lblProductName.setText("SPOS -");
        panelHeader.add(lblProductName);

        lblModuleName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblModuleName.setForeground(new java.awt.Color(255, 255, 255));
        panelHeader.add(lblModuleName);

        lblformName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblformName.setForeground(new java.awt.Color(255, 255, 255));
        lblformName.setText("- Close Production Order");
        panelHeader.add(lblformName);
        panelHeader.add(filler4);
        panelHeader.add(filler5);

        lblPosName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblPosName.setForeground(new java.awt.Color(255, 255, 255));
        lblPosName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPosName.setMaximumSize(new java.awt.Dimension(321, 30));
        lblPosName.setMinimumSize(new java.awt.Dimension(321, 30));
        lblPosName.setPreferredSize(new java.awt.Dimension(321, 30));
        panelHeader.add(lblPosName);
        panelHeader.add(filler6);

        lblUserCode.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblUserCode.setForeground(new java.awt.Color(255, 255, 255));
        lblUserCode.setMaximumSize(new java.awt.Dimension(90, 30));
        lblUserCode.setMinimumSize(new java.awt.Dimension(90, 30));
        lblUserCode.setPreferredSize(new java.awt.Dimension(90, 30));
        panelHeader.add(lblUserCode);

        lblDate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblDate.setForeground(new java.awt.Color(255, 255, 255));
        lblDate.setMaximumSize(new java.awt.Dimension(192, 30));
        lblDate.setMinimumSize(new java.awt.Dimension(192, 30));
        lblDate.setPreferredSize(new java.awt.Dimension(192, 30));
        panelHeader.add(lblDate);

        lblHOSign.setMaximumSize(new java.awt.Dimension(34, 30));
        lblHOSign.setMinimumSize(new java.awt.Dimension(34, 30));
        lblHOSign.setPreferredSize(new java.awt.Dimension(34, 30));
        panelHeader.add(lblHOSign);

        getContentPane().add(panelHeader, java.awt.BorderLayout.PAGE_START);

        panelMainForm.setMinimumSize(new java.awt.Dimension(800, 570));
        panelMainForm.setOpaque(false);
        panelMainForm.setLayout(new java.awt.GridBagLayout());

        panelFormBody.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204), new java.awt.Color(153, 153, 153), new java.awt.Color(153, 153, 153)));
        panelFormBody.setMinimumSize(new java.awt.Dimension(800, 570));
        panelFormBody.setOpaque(false);
        panelFormBody.setPreferredSize(new java.awt.Dimension(800, 570));

        lbFormName.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lbFormName.setText("Close Production Order");

        txtProductionOrderNo.setEnabled(false);
        txtProductionOrderNo.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                txtProductionOrderNoMouseClicked(evt);
            }
        });

        lblBillNo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblBillNo.setText("Select Production Order No.");

        btnCancel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCancel.setForeground(new java.awt.Color(255, 255, 255));
        btnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/POSTransaction/images/imgCommonBtnLong1.png"))); // NOI18N
        btnCancel.setText("CLOSE");
        btnCancel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCancel.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/POSTransaction/images/imgCommonBtnLong2.png"))); // NOI18N
        btnCancel.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                btnCancelMouseClicked(evt);
            }
        });

        btnCloseProdOrder.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCloseProdOrder.setForeground(new java.awt.Color(255, 255, 255));
        btnCloseProdOrder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/POSTransaction/images/imgCommonBtnLong1.png"))); // NOI18N
        btnCloseProdOrder.setText("<html>CLOSE<br>ORDER</html>");
        btnCloseProdOrder.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCloseProdOrder.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/POSTransaction/images/imgCommonBtnLong2.png"))); // NOI18N
        btnCloseProdOrder.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                btnCloseProdOrderMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelFormBodyLayout = new javax.swing.GroupLayout(panelFormBody);
        panelFormBody.setLayout(panelFormBodyLayout);
        panelFormBodyLayout.setHorizontalGroup(
            panelFormBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFormBodyLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnCloseProdOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
            .addGroup(panelFormBodyLayout.createSequentialGroup()
                .addGroup(panelFormBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFormBodyLayout.createSequentialGroup()
                        .addGap(268, 268, 268)
                        .addComponent(lbFormName, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelFormBodyLayout.createSequentialGroup()
                        .addGap(202, 202, 202)
                        .addComponent(lblBillNo, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(txtProductionOrderNo, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(222, Short.MAX_VALUE))
        );
        panelFormBodyLayout.setVerticalGroup(
            panelFormBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormBodyLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(lbFormName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addGroup(panelFormBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBillNo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtProductionOrderNo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(287, 287, 287)
                .addGroup(panelFormBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCloseProdOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(76, Short.MAX_VALUE))
        );

        panelMainForm.add(panelFormBody, new java.awt.GridBagConstraints());

        getContentPane().add(panelMainForm, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtProductionOrderNoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtProductionOrderNoMouseClicked
        // TODO add your handling code here:
        try
        {
            objUtility.funCallForSearchForm("CloseProductionOrder");
            new frmSearchFormDialog(this,true).setVisible(true);
            if(clsGlobalVarClass.gSearchItemClicked)
            {
                Object[] data=clsGlobalVarClass.gArrListSearchData.toArray();
                txtProductionOrderNo.setText(data[0].toString());
                clsGlobalVarClass.gSearchItemClicked=false;
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }//GEN-LAST:event_txtProductionOrderNoMouseClicked

    private void btnCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseClicked
        // TODO add your handling code here:
        dispose();
        clsGlobalVarClass.hmActiveForms.remove("Close Producion Order");
    }//GEN-LAST:event_btnCancelMouseClicked

    private void btnCloseProdOrderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseProdOrderMouseClicked
        // TODO add your handling code here:
        funUnsettleBill();
    }//GEN-LAST:event_btnCloseProdOrderMouseClicked

    private void formWindowClosed(java.awt.event.WindowEvent evt)//GEN-FIRST:event_formWindowClosed
    {//GEN-HEADEREND:event_formWindowClosed
        clsGlobalVarClass.hmActiveForms.remove("Close Producion Order");
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt)//GEN-FIRST:event_formWindowClosing
    {//GEN-HEADEREND:event_formWindowClosing
        clsGlobalVarClass.hmActiveForms.remove("Close Producion Order");
    }//GEN-LAST:event_formWindowClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCloseProdOrder;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler6;
    private javax.swing.JLabel lbFormName;
    private javax.swing.JLabel lblBillNo;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblHOSign;
    private javax.swing.JLabel lblModuleName;
    private javax.swing.JLabel lblPosName;
    private javax.swing.JLabel lblProductName;
    private javax.swing.JLabel lblUserCode;
    private javax.swing.JLabel lblformName;
    private javax.swing.JPanel panelFormBody;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JPanel panelMainForm;
    private javax.swing.JTextField txtProductionOrderNo;
    // End of variables declaration//GEN-END:variables
}
