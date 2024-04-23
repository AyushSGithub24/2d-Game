package tile;



import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
	GamePanel gp;
	Tile[] tile;
	int[][] mapTileNum;
	public TileManager(GamePanel gp) {
		this.gp=gp;
		tile =new Tile[10];
		mapTileNum=new int[gp.maxScreenRow][gp.maxScreenCol];
		loadMap("/maps/map01.txt");
		getTileImage();
		
		
	}
	public void loadMap(String filePath) {
		try {
			InputStream is=getClass().getResourceAsStream(filePath);
			BufferedReader br =new BufferedReader(new InputStreamReader(is));
			
			int row=0,col=0;
			while(row<gp.maxScreenRow && col<gp.maxScreenCol) {
				String str=br.readLine();
				while(col<gp.maxScreenCol) {
					String number[]=str.split(" ");
					int num=Integer.parseInt(number[col]);
					mapTileNum[row][col]=num;
					col++;
				}
				if(col==gp.maxScreenCol) {
					col=0;
					row++;
				}
			}
			br.close();
		}catch(Exception e) {
			
		}
	}
	public void getTileImage() {
		try {
			tile[0]=new Tile();
			tile[0].image=ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
			tile[1]=new Tile();
			tile[1].image=ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
			tile[2]=new Tile();
			tile[2].image=ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
			tile[3]=new Tile();
			tile[3].image=ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));
			tile[4]=new Tile();
			tile[4].image=ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
			tile[5]=new Tile();
			tile[5].image=ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void draw(Graphics2D g2) {
		
		int row=0,col=0;
		int x=0,y=0;
		while(row<gp.maxScreenRow && col<gp.maxScreenCol) {
			int number=mapTileNum[row][col];
			g2.drawImage(tile[number].image ,x, y, gp.tileSize, gp.tileSize, null);
			x+=gp.tileSize;
			col++;
			if(col==gp.maxScreenCol) {
				col=0;
				y+=gp.tileSize;
				x=0;
				row++;
			}
		}
		
		
	}
	
	
	
	
	
	
}
