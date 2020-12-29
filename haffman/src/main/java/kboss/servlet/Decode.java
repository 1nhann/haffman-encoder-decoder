package kboss.servlet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kboss.app.HaffmanTree;
import kboss.app.HaffmanTreeImpl;
import kboss.app.HuffmanTreeBuilder;
import kboss.dsaj.binTree.BinTree;

//10101 to string
public class Decode extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HaffmanTree haff;
        resp.setContentType("text/html;charset=UTF-8");
        String useDefault = req.getParameter("use-default");
        req.setAttribute("use-default", useDefault);
        String text = req.getParameter("text-to-decode");
        String charWeightPairs = req.getParameter("decode-weights");
        try{
            if(useDefault == null){haff = new HaffmanTreeImpl(charWeightPairs);}
            else{
                String path = this.getServletContext().getRealPath("data.txt");
                //FileInputStream in=new FileInputStream(path);
                BufferedReader in = new BufferedReader(new FileReader(path));
                charWeightPairs = in.readLine();
                haff = new HaffmanTreeImpl(charWeightPairs);
                in.close();
            }
            String m = haff.decode(text);
            String haffInfo = haff.showTree();
            req.setAttribute("decoded-text", m);
            req.setAttribute("haffInfo", haffInfo);
        }
        catch(Exception e){
            req.setAttribute("error", e.getMessage());
        }
        req.setAttribute("text-to-decode", text);
        req.setAttribute("char-weights-pairs", charWeightPairs);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
    
}
