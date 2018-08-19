import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.*; 
import java.io.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Heromon extends PApplet {




public class Person {
  public double health  = 100;
  public double strength = 50;
  public double maxHealth = 100;
  public double maxStrength = 100;

  public ArrayList<String> abilities = new ArrayList<String>();

  //pos[0] = x, pos[1] = y
  public int[] pos = new int[2];

  //r = rgb[0], g = rgb[1], b = rgb[2]
  public int[] rgb = new int[]{255, 255, 255};

  //index 0 = punch, 1 = kick, 2 = block, and 3 = ult
  public int[] durations = new int[]{10, 10, 40, 10};
  public int[] cooldowns = new int[]{0, 80, 0, 300};
  public int[] defaultCooldowns = new int[]{10, 80, 80, 300};
  public boolean[] onCooldown = new boolean[]{false, true, false, true};
  
  //0 = punch, 1 = kick, 2 = block, 3 = ult, 4 = idle, 5 = do nothing
  public int attacking = 5;
  public int attack = 4;

  public boolean crit = false;
  public boolean empowered = false;
  public boolean lifesteal = false;

  public double punchDmg =1;
  public double kickDmg = 2;
  public double ultDmg = 5;

  public int currentUlt = 0;

  public int originalX;

  Person(int x, int y) {
    strength = 50;
    this.pos[0] = x;
    this.pos[1] = y;
    originalX = x;
  }
  
  public void idle() {
    this.pos[0] = this.originalX;
    strokeWeight(4);
    stroke(0, 0, 0);
    fill(rgb[0], rgb[1], rgb[2]);
  }
  
  public void kick() {
    strokeWeight(4);
    stroke(0, 0, 0);
    fill(rgb[0], rgb[1], rgb[2]);
  }
  
  public void punch() {
    strokeWeight(4);
    stroke(0, 0, 0);
    fill(rgb[0], rgb[1], rgb[2]);
  }
  
  public void block() {
    strokeWeight(4);
    stroke(0, 0, 0);
    fill(rgb[0], rgb[1], rgb[2]);
  }
  
  public void ult(int ultID) {
    this.currentUlt = ultID;
    strokeWeight(4);
    stroke(0, 0, 0);
    fill(rgb[0], rgb[1], rgb[2]);
  }
  
  public double getHealth() {
    return this.health;
  }
  
  public double getStrength() {
    return this.strength;
  }
  
  public void loseHealth(int hp) {
    this.health -= hp;
  }
  
  public void addHealth(int hp) {
    this.health += hp;
  }
  
  public void loseStrength(int s) {
    this.strength -= s;
  }
  
  public void addStrength(int s) {
    this.strength += s;
  }

  public void attackMechanics(Person m) {

    // attack logic

    this.ultDmg = 5;
    this.currentUlt = 0;
    if (this.abilities.contains("High Kick")) {
      this.ultDmg = 7;
      this.currentUlt = 1;
    } 
    if (this.abilities.contains("Sword")) {
      this.ultDmg = (m.maxHealth-m.health) * 0.2f;
      this.currentUlt = 2;
    } 

    if (this instanceof Hero && !endGame) {   
      switch(attack) {
      case 0:
        saves.println("q");
        break;
      case 1:
        saves.println("w");
        break;
      case 2:
        saves.println("e");
        break;
      case 3:
        saves.println("r");
        break;
      case 4:
        saves.println("i");
        break;
      default:  
        saves.println("d");
      }
    }


    //combos

    //boxing
    if (this.attacking == 2 && this.abilities.contains("Boxing") && this.attack == 0) {
      this.durations[2] = 0;
      this.onCooldown[2] = true;
      this.cooldowns[2] = 80;
      this.attack = 0;
      this.attacking = 5;
      this.crit = true;
      this.pos[0] = this.originalX;
    }
    //kickboxing
    if (this.attacking == 0 && this.abilities.contains("Kickboxing") && this.attack == 1) {
      this.durations[0] = 0;
      this.onCooldown[0] = true;
      this.cooldowns[0] = 10;
      this.attack = 1;
      this.attacking = 5;
      this.empowered = true;
      this.pos[0] = this.originalX;
    }
    //ninjitsu
    if (this.attacking == 2 && this.abilities.contains("Ninjitsu") && this.attack == 3) {
      this.durations[2] = 0;
      this.onCooldown[2] = true;
      this.cooldowns[2] = 80;
      this.attack = 3;
      this.attacking = 5;
      this.crit = true;
      this.pos[0] = this.originalX;
    }
    //Muay Thai
    if (this.attacking == 3 && this.abilities.contains("Muay Thai") && this.attack == 2) {
      this.durations[3] = 0;
      this.onCooldown[3] = true;
      this.cooldowns[3] = 300;
      this.attack = 2;
      this.attacking = 5;
      this.empowered = true;
      this.pos[0] = this.originalX;
    }
    //Taekwondo
    if (this.attacking == 3 && this.abilities.contains("Taekwondo") && this.attack == 1) {
      this.durations[3] = 0;
      this.onCooldown[3] = true;
      this.cooldowns[3] = 300;
      this.attack = 1;
      this.attacking = 5;
      this.crit = true;
      this.pos[0] = this.originalX;
    }

    //Bushido
    if (this.attacking == 0 && this.abilities.contains("Bushido") && this.attack == 3) {
      this.durations[0] = 0;
      this.onCooldown[0] = true;
      this.cooldowns[0] = 10;
      this.attack = 3;
      this.attacking = 5;
      this.lifesteal = true;
      this.pos[0] = this.originalX;
    }

    //attack starts the attack animation
    switch(attack) {
    case 4:
      this.idle();
      break;

    case 0:
      if (this instanceof Hero)
        this.pos[0]+=45;
      else
        this.pos[0]-=45;
      this.strength-=1;
      if (m.attacking != 2 && !this.crit) {
        m.health-=this.punchDmg;
        pings.add(new Ping("-1 health", width/2 -300 + m.originalX*2, 350));
      } else if (m.attacking == 2 && !this.crit) {
        pings.add(new Ping("punch blocked!", width/2 -300 + m.originalX*2, 350));
      } else if (m.attacking != 2 && this.crit) {
        m.health-=this.punchDmg * 2;
        pings.add(new Ping("**CRIT**\n-2 health", width/2 -300 + m.originalX*2, 350));
      } else if (m.attacking == 2 && this.crit) {
        m.health-=this.punchDmg /2;
        pings.add(new Ping("critical punch blocked!\n-0.5 health", width/2 -300 + m.originalX*2, 350));
      }
      this.attacking = 0;
      this.onCooldown[0] = true;
      this.cooldowns[0] = this.defaultCooldowns[0];
      this.attack = 5;
      this.crit = false;
      break;

    case 1:
      if (this instanceof Hero)
        this.pos[0]+=45;
      else
        this.pos[0]-=45;
      if (m.attacking != 2 && !this.empowered && !this.crit) {
        m.health-=this.kickDmg;
        pings.add(new Ping("-2 health", width/2 -300 + m.originalX*2, 350));
      } else if (m.attacking == 2 && !this.empowered && !this.crit) {
        pings.add(new Ping("kick blocked!", width/2 -300 + m.originalX*2, 350));
      } else if (m.attacking != 2 && this.empowered) {
        m.health-=this.kickDmg * 2;
        pings.add(new Ping("EMPOWERED KICK\n-4 health", width/2 -300 + m.originalX*2, 350));
      } else if (m.attacking == 2 && this.empowered) {
        this.strength += 2;
        pings.add(new Ping("empowered kick blocked", width/2 -300 + m.originalX*2, 350));
      } else if (m.attacking != 2 && this.crit) {
        m.health-=this.kickDmg * 2;
        pings.add(new Ping("**CRIT**\n-4 health", width/2 -300 + m.originalX*2, 350));
      } else if (m.attacking == 2 && this.crit) {
        m.health-=this.kickDmg / 2;
        pings.add(new Ping("crit blocked!\n-1 health", width/2 -300 + m.originalX*2, 350));
      }
      this.strength-=2;
      this.attacking = 1;
      this.onCooldown[1] = true;
      this.cooldowns[1] = this.defaultCooldowns[1];
      this.attack = 5;
      this.empowered = false;
      this.crit = false;
      break;

    case 2:
      if (this instanceof Hero)
        this.pos[0]-=20;
      else
        this.pos[0]+=20;
      this.durations[2] = 40;
      this.attacking = 2;
      this.attack = 5;
      break;

    case 3:
      if (this instanceof Hero)
        this.pos[0]+=25;
      else
        this.pos[0]-=25;
      if (m.attacking != 2 && !this.crit) {
        m.health-=this.ultDmg;
        pings.add(new Ping("-" + (int)this.ultDmg + " health", width/2 -300 + m.originalX*2, 350));
      } else if (m.attacking == 2 && !this.crit) {
        pings.add(new Ping(ults[this.currentUlt] + " blocked!", width/2 -300 + m.originalX*2, 350));
      } else if (m.attacking != 2 && this.crit) {
        m.health -= this.ultDmg * 2;
        pings.add(new Ping("**CRIT**\n-" + (int)(this.ultDmg * 2) + " health", width/2 -300 + m.originalX*2, 350));
      } else if (m.attacking == 2 && this.crit) {
        m.health -= this.ultDmg/2;
        pings.add(new Ping("crit blocked!\n-" + (int)(this.ultDmg / 2) + " health", width/2 -300 + m.originalX*2, 350));
      }
      if (this.lifesteal && m.attacking != 2) {
        this.health+=this.ultDmg * 0.2f;
        pings.add(new Ping("Lifesteal:\n+" + (int)(this.ultDmg * 0.3f) + " health", width/2 +300 - mon.originalX*2, 350));
      }
      this.attacking = 3;
      this.onCooldown[3] = true;
      this.cooldowns[3] = this.defaultCooldowns[3];
      this.attack = 5;
      this.crit = false;
      this.lifesteal = false;
      break;

    default:
      break;
    }

    //attacking plays attack animation, causes delay between attacks
    switch(attacking) {
    case 0:
      this.punch();
      if (this.durations[0] >= 0) {
        this.durations[0]-=2;
      }
      if (this.durations[0] <= 0) {
        this.onCooldown[0] = true;
        this.cooldowns[0] = this.defaultCooldowns[0];
        this.attack = 4;
        this.attacking = 5;
        this.durations[0] = 10;
        this.pos[0] = this.originalX;
      }
      break;

    case 1:
      this.kick();
      if (this.durations[1] >= 0) {
        this.durations[1]-=2;
      }
      if (this.durations[1] <= 0) {
        this.onCooldown[1] = true;
        this.cooldowns[1] = this.defaultCooldowns[1];
        this.attack = 4;
        this.attacking = 5;
        this.durations[1] = 10;
        this.pos[0] = this.originalX;
      }
      break;

    case 2:
      this.block();
      if (m.attacking == 3) 
        this.strength+=(this.maxStrength-this.strength) * 0.025f;
      if (this.empowered && m.attack == 0) {
        this.strength+=1;
        pings.add(new Ping("empowered block!\n+1 strength", width/2 +300 - mon.originalX*2, 350));
      }
      if (this.empowered && m.attack == 0) {
        this.strength+=2;
        pings.add(new Ping("empowered block!\n+2 strength", width/2 +300 - mon.originalX*2, 350));
      }
      if (this.durations[2]>= 0) {
        this.durations[2]-=1;
      }
      if (this.durations[2] <= 0) {
        this.onCooldown[2] = true;
        this.cooldowns[2] = this.defaultCooldowns[2];
        this.attack = 4;
        this.attacking = 5;
        this.empowered = false;
        this.pos[0] = this.originalX;
      }
      break;

    case 3:
      this.ult(currentUlt);
      if (this.durations[3] >= 0) {
        this.durations[3]-=1;
      }
      if (this.durations[3] <= 0) {
        this.onCooldown[3] = true;
        this.cooldowns[3] = this.defaultCooldowns[3];
        this.attack = 4;
        this.attacking = 5;
        this.durations[3] = 10;
        this.pos[0] = this.originalX;
      }
      break;

    default:
      break;
    }

    //what to do if on cooldowns
    if (this.onCooldown[0]) {
      if (this.cooldowns[0]>0) {
        this.cooldowns[0]-=1;
      }
      if (this.cooldowns[0] <= 0) {
        this.onCooldown[0] = false;
      }
    }

    if (this.onCooldown[1]) {
      if (this.cooldowns[1]>0) {
        this.cooldowns[1]-=1;
      }
      if (this.cooldowns[1] <= 0) {
        this.onCooldown[1] = false;
      }
    }

    if (this.onCooldown[2]) {
      if (this.cooldowns[2]>0) {
        this.cooldowns[2]-=1;
      }
      if (this.cooldowns[2] <= 0) {
        this.onCooldown[2] = false;
      }
    }

    if (this.onCooldown[3]) {
      if (this.cooldowns[3]>0) {
        this.cooldowns[3]-=1;
      }
      if (this.cooldowns[3] <= 0) {
        this.onCooldown[3] = false;
      }
    }
  }
}

public class Hero extends Person {
  Hero(int x, int y) {
    super(x, y);
    health = 100;
    switch (currentChampion){
      case 0:
        rgb = new int[]{255, 255, 255};
        break;
      case 1:
        rgb = new int[]{45, 45, 45};
        break;
      default:
        rgb = new int[]{255, 255, 255};
        break;
    }
  }

  public void idle() {
    if(currentChampion == 2){
      pushMatrix();
      scale(1.2f);
      translate(-100,-100);
    }
    super.idle();
    ellipse(pos[0]+width/2-100, pos[1]+300, 100, 100);//head
    point(pos[0]+width/2-75, pos[1]+290);//epos[1]e
    line(pos[0]+width/2-80, pos[1]+285, pos[0]+width/2-70, pos[1]+288);
    line(pos[0]+width/2-65, pos[1]+315, pos[0]+width/2-70, pos[1]+315);//mouth
    line(pos[0]+width/2-125, pos[1]+345, pos[0]+width/2-150, pos[1]+475);//bodpos[1]
    line(pos[0]+width/2-150, pos[1]+475, pos[0]+width/2-100, pos[1]+525);//leg
    line(pos[0]+width/2-100, pos[1]+525, pos[0]+width/2-125, pos[1]+600);
    line(pos[0]+width/2-150, pos[1]+475, pos[0]+width/2-120, pos[1]+515);//leg
    line(pos[0]+width/2-120, pos[1]+515, pos[0]+width/2-155, pos[1]+610);
    line(pos[0]+width/2-130, pos[1]+375, pos[0]+width/2-120, pos[1]+415);//arm
    line(pos[0]+width/2-120, pos[1]+415, pos[0]+width/2-80, pos[1]+375);
    line(pos[0]+width/2-130, pos[1]+375, pos[0]+width/2-108, pos[1]+412);//arm
    line(pos[0]+width/2-108, pos[1]+412, pos[0]+width/2-70, pos[1]+375);
    if(currentChampion == 2)
      popMatrix();
  }

  public void punch() {
    if(currentChampion == 2){
      pushMatrix();
      scale(1.2f);
      translate(-100,-100);
    }
    super.punch();
    ellipse(pos[0]+width/2-100, pos[1]+300, 100, 100);//head
    point(pos[0]+width/2-75, pos[1]+290);//epos[1]e
    line(pos[0]+width/2-80, pos[1]+285, pos[0]+width/2-70, pos[1]+288);
    line(pos[0]+width/2-65, pos[1]+315, pos[0]+width/2-70, pos[1]+315);//mouth
    line(pos[0]+width/2-125, pos[1]+345, pos[0]+width/2-150, pos[1]+475);//bodpos[1]
    line(pos[0]+width/2-150, pos[1]+475, pos[0]+width/2-100, pos[1]+525);//leg
    line(pos[0]+width/2-100, pos[1]+525, pos[0]+width/2-125, pos[1]+600);
    line(pos[0]+width/2-150, pos[1]+475, pos[0]+width/2-120, pos[1]+515);//leg
    line(pos[0]+width/2-120, pos[1]+515, pos[0]+width/2-155, pos[1]+610);
    line(pos[0]+width/2-130, pos[1]+375, pos[0]+width/2-125, pos[1]+420);//arm
    line(pos[0]+width/2-125, pos[1]+420, pos[0]+width/2-85, pos[1]+380);
    line(pos[0]+width/2-130, pos[1]+375, pos[0]+width/2-38, pos[1]+370);//arm
    if(currentChampion == 2)
      popMatrix();
  }

  public void kick () {
    if(currentChampion == 2){
      pushMatrix();
      scale(1.2f);
      translate(-100,-100);
    }
    super.kick();
    ellipse(pos[0]+width/2-130, pos[1]+330, 100, 100);//head
    point(pos[0]+width/2-105, pos[1]+320);//epos[1]e
    line(pos[0]+width/2-110, pos[1]+315, pos[0]+width/2-100, pos[1] +318);
    line(pos[0]+width/2-95, pos[1]+345, pos[0]+width/2-100, pos[1] +345);//mouth
    line(pos[0]+width/2-130, pos[1]+380, pos[0]+width/2 - 90, pos[1]+485);//bodpos[1]
    line(pos[0]+width/2-90, pos[1]+ 485, pos[0]+width/2 - 160, pos[1]+ 510);//leg
    line(pos[0]+width/2-160, pos[1]+510, pos[0]+width/2 - 100, pos[1]+ 585);
    line(pos[0]+width/2 - 90, pos[1]+485, pos[0]+width/2 + 15, pos[1]+420);//leg
    line(pos[0]+width/2-140, pos[1]+440, pos[0]+width/2 - 120, pos[1]+405);//arm
    line(pos[0]+width/2-140, pos[1]+395, pos[0]+width/2-140, pos[1]+440);
    line(pos[0]+width/2-120, pos[1]+405, pos[0]+width/2-75, pos[1]+425);//arm
    line(pos[0]+width/2-75, pos[1]+425, pos[0]+width/2 - 110, pos[1]+390);
    if(currentChampion == 2)
      popMatrix();
  }

  public void block() {
    if(currentChampion == 2){
      pushMatrix();
      scale(1.2f);
      translate(-100,-100);
    }
    super.block();
    line(pos[0]+width/2-125, pos[1]+345, pos[0]+width/2-150, pos[1]+475);//bodpos[1]
    line(pos[0]+width/2-150, pos[1]+475, pos[0]+width/2-100, pos[1]+525);//leg
    line(pos[0]+width/2-100, pos[1]+525, pos[0]+width/2-125, pos[1]+600);
    line(pos[0]+width/2-150, pos[1]+475, pos[0]+width/2-120, pos[1]+515);//leg
    line(pos[0]+width/2-120, pos[1]+515, pos[0]+width/2-155, pos[1]+610);

    line(pos[0]+width/2-130, pos[1]+375, pos[0]+width/2-65, pos[1]+365);//arm
    line(pos[0]+width/2-65, pos[1]+365, pos[0]+width/2-60, pos[1]+290);
    ellipse(pos[0]+width/2-100, pos[1]+300, 100, 100);//head
    point(pos[0]+width/2-75, pos[1]+290);//epos[1]e
    line(pos[0]+width/2-80, pos[1]+285, pos[0]+width/2-70, pos[1]+288);
    line(pos[0]+width/2-65, pos[1]+315, pos[0]+width/2-70, pos[1]+315);//mouth
    line(pos[0]+width/2-130, pos[1]+375, pos[0]+width/2-70, pos[1]+355);//arm
    line(pos[0]+width/2-70, pos[1]+355, pos[0]+width/2-65, pos[1]+290);
    if(currentChampion == 2)
      popMatrix();
  }

  public void ult(int ultID) {
    if(currentChampion == 2){
      pushMatrix();
      scale(1.2f);
      translate(-130,-100);
    }
    super.ult(ultID);
    switch(currentUlt) {
    case 0:
      ellipse(pos[0]+width/2-80, pos[1]+260, 100, 100);//head
      point(pos[0]+width/2-55, pos[1]+250);//epos[1]e
      line(pos[0]+width/2-60, pos[1]+245, pos[0]+width/2-50, pos[1]+248);
      line(pos[0]+width/2-45, pos[1]+275, pos[0]+width/2-50, pos[1]+275);//mouth
      line(pos[0]+width/2-80, pos[1]+310, pos[0]+width/2-65, pos[1]+450);//bodpos[1]
      line(pos[0]+width/2-65, pos[1]+450, pos[0]+width/2-70, pos[1]+ 515);//leg
      line(pos[0]+width/2 - 70, pos[1]+ 515, pos[0]+width/2-85, pos[1]+ 570);
      line(pos[0]+width/2-65, pos[1]+450, pos[0]+width/2 - 10, pos[1]+ 380);//leg
      line(pos[0]+width/2-10, pos[1]+380, pos[0]+width/2 - 20, pos[1]+455);
      line(pos[0]+width/2 - 79, pos[1]+330, pos[0]+width/2 - 84, pos[1]+ 385);//arm
      line(pos[0]+width/2 - 84, pos[1]+385, pos[0]+width/2 - 60, pos[1]+360);
      line(pos[0]+width/2 - 79, pos[1]+330, pos[0]+width/2 - 60, pos[1]+380);//arm
      line(pos[0]+width/2 - 60, pos[1]+380, pos[0]+width/2 - 50, pos[1]+335);
      break;

    case 1:
      ellipse(pos[0]+width/2-80, pos[1]+260, 100, 100);//head
      point(pos[0]+width/2-55, pos[1]+250);//epos[1]e
      line(pos[0]+width/2-60, pos[1]+245, pos[0]+width/2-50, pos[1]+248);
      line(pos[0]+width/2-45, pos[1]+275, pos[0]+width/2-50, pos[1]+275);//mouth
      line(pos[0]+width/2-80, pos[1]+310, pos[0]+width/2-65, pos[1]+450);//bodpos[1]
      line(pos[0]+width/2-65, pos[1]+450, pos[0]+width/2-70, pos[1]+ 515);//leg
      line(pos[0]+width/2 - 70, pos[1]+ 515, pos[0]+width/2-85, pos[1]+ 570);
      line(pos[0]+width/2-65, pos[1]+450, pos[0]+width/2 + 25, pos[1]+ 320);//leg
      line(pos[0]+width/2 - 79, pos[1]+330, pos[0]+width/2 - 84, pos[1]+ 385);//arm
      line(pos[0]+width/2 - 84, pos[1]+385, pos[0]+width/2 - 60, pos[1]+360);
      line(pos[0]+width/2 - 79, pos[1]+330, pos[0]+width/2 - 60, pos[1]+380);//arm
      line(pos[0]+width/2 - 60, pos[1]+380, pos[0]+width/2 - 50, pos[1]+335);
      break;
    case 2:
      super.punch();
      ellipse(pos[0]+width/2-100, pos[1]+300, 100, 100);//head
      point(pos[0]+width/2-75, pos[1]+290);//epos[1]e
      line(pos[0]+width/2-80, pos[1]+285, pos[0]+width/2-70, pos[1]+288);
      line(pos[0]+width/2-65, pos[1]+315, pos[0]+width/2-70, pos[1]+315);//mouth
      line(pos[0]+width/2-125, pos[1]+345, pos[0]+width/2-150, pos[1]+475);//bodpos[1]
      line(pos[0]+width/2-150, pos[1]+475, pos[0]+width/2-100, pos[1]+525);//leg
      line(pos[0]+width/2-100, pos[1]+525, pos[0]+width/2-125, pos[1]+600);
      line(pos[0]+width/2-150, pos[1]+475, pos[0]+width/2-120, pos[1]+515);//leg
      line(pos[0]+width/2-120, pos[1]+515, pos[0]+width/2-155, pos[1]+610);
      line(pos[0]+width/2-132, pos[1]+373, pos[0]+width/2-155, pos[1]+420);//arm
      line(pos[0]+width/2-155, pos[1]+420, pos[0]+width/2-110, pos[1]+410);
      line(pos[0]+width/2-130, pos[1]+375, pos[0]+width/2-38, pos[1]+370);//arm
      line(pos[0]+width/2-45, pos[1]+375, pos[0]+width/2+85, pos[1]+325);//sword
      line(pos[0]+width/2 - 35, pos[1]+365, pos[0]+width/2- 31, pos[1] +376);
    default:
      break;
    }
    if(currentChampion == 2)
      popMatrix();
  }
}

public class Monster extends Person {
  Monster(int x, int y) {
    super(x, y);
    health = 100;

    switch(level) {
    case 0:
      rgb = new int[]{5, 155, 5};
      break;
    case 1:
      rgb = new int[]{5, 5, 155};
      break;
    case 2:
      rgb = new int[]{155, 155, 155};
      break;
    case 3:
      rgb = new int[]{155, 5, 5};
      break;
    case 4:
      rgb = new int[]{155,5,5};
      break;
    default:
      rgb = new int[]{5, 155, 5};
      break;
    }
  }
  public void idle() {
    if(level == 4){
      pushMatrix();
      scale(1.2f);
      translate(-130,-100);
    }
    super.idle();
    ellipse(pos[0]+width/2+100-300, pos[1]+300, 100, 100);//head
    point(pos[0]+width/2+75-300, pos[1] +290);//epos[1]e
    line(pos[0]+width/2+80-300, pos[1]+285, pos[0]+width/2+70-300, pos[1]+288);
    line(pos[0]+width/2+65-300, pos[1]+315, pos[0]+width/2+70-300, pos[1]+315);//mouth
    line(pos[0]+width/2+125-300, pos[1]+345, pos[0]+width/2+150-300, pos[1]+475);//bodpos[1]
    line(pos[0]+width/2+150-300, pos[1]+475, pos[0]+width/2+100-300, pos[1]+525);//leg
    line(pos[0]+width/2+100-300, pos[1]+525, pos[0]+width/2+125-300, pos[1]+600);
    line(pos[0]+width/2+150-300, pos[1]+475, pos[0]+width/2+120-300, pos[1]+515);//leg
    line(pos[0]+width/2+120-300, pos[1]+515, pos[0]+width/2+155-300, pos[1]+610);
    line(pos[0]+width/2+130-300, pos[1]+375, pos[0]+width/2+120-300, pos[1]+415);//arm
    line(pos[0]+width/2+120-300, pos[1]+415, pos[0]+width/2+80-300, pos[1]+375);
    line(pos[0]+width/2+130-300, pos[1]+375, pos[0]+width/2+108-300, pos[1]+412);//arm
    line(pos[0]+width/2+108-300, pos[1]+412, pos[0]+width/2+70-300, pos[1]+375);
    if(level == 4)
      popMatrix();
  }

  public void punch() {
    if(level == 4){
      pushMatrix();
      scale(1.2f);
      translate(-130,-100);
    }
    super.punch();
    ellipse(pos[0]+width/2+100-300, pos[1]+300, 100, 100);//head
    point(pos[0]+width/2+75-300, pos[1]+290);//epos[1]e
    line(pos[0]+width/2+80-300, pos[1]+285, pos[0]+width/2+70-300, pos[1]+288);
    line(pos[0]+width/2+65-300, pos[1]+315, pos[0]+width/2+70-300, pos[1]+315);//mouth
    line(pos[0]+width/2+125-300, pos[1]+345, pos[0]+width/2+150-300, pos[1]+475);//bodpos[1]
    line(pos[0]+width/2+150-300, pos[1]+475, pos[0]+width/2+100-300, pos[1]+525);//leg
    line(pos[0]+width/2+100-300, pos[1]+525, pos[0]+width/2+125-300, pos[1]+600);
    line(pos[0]+width/2+150-300, pos[1]+475, pos[0]+width/2+120-300, pos[1]+515);//leg
    line(pos[0]+width/2+120-300, pos[1]+515, pos[0]+width/2+155-300, pos[1]+610);
    line(pos[0]+width/2+130-300, pos[1]+375, pos[0]+width/2+125-300, pos[1]+420);//arm
    line(pos[0]+width/2+125-300, pos[1]+420, pos[0]+width/2+85-300, pos[1]+380);
    line(pos[0]+width/2+130-300, pos[1]+375, pos[0]+width/2+38-300, pos[1]+370);//arm
    if(level == 4)
      popMatrix();
  }

  public void kick () {
    if(level == 4){
      pushMatrix();
      scale(1.2f);
      translate(-130,-100);
    }
    super.kick();
    ellipse(pos[0]+width/2+130-300, pos[1]+330, 100, 100);//head
    point(pos[0]+width/2+105-300, pos[1]+320);//epos[1]e
    line(pos[0]+width/2+110-300, pos[1]+315, pos[0]+width/2+100-300, pos[1] + 318);
    line(pos[0]+width/2+95-300, pos[1]+345, pos[0]+width/2+100-300, pos[1] + 345);//mouth
    line(pos[0]+width/2+130-300, pos[1]+380, pos[0]+width/2 + 90-300, pos[1]+485);//bodpos[1]
    line(pos[0]+width/2+90-300, pos[1]+ 485, pos[0]+width/2+ 160-300, pos[1]+ 510);//leg
    line(pos[0]+width/2+160-300, pos[1]+510, pos[0]+width/2 + 100-300, pos[1]+ 585);
    line(pos[0]+width/2 + 90-300, pos[1]+485, pos[0]+width/2 - 15-300, pos[1]+420);//leg
    line(pos[0]+width/2+140-300, pos[1]+440, pos[0]+width/2+ 120-300, pos[1]+405);//arm
    line(pos[0]+width/2+140-300, pos[1]+395, pos[0]+width/2+140-300, pos[1]+440);
    line(pos[0]+width/2+120-300, pos[1]+405, pos[0]+width/2+75-300, pos[1]+425);//arm
    line(pos[0]+width/2+75-300, pos[1]+425, pos[0]+width/2 + 110-300, pos[1]+390);
    if(level == 4)
      popMatrix();
  }

  public void block() {
    if(level == 4){
      pushMatrix();
      scale(1.2f);
      translate(-130,-100);
    }
    super.block();
    line(pos[0]+width/2+125-300, pos[1]+345, pos[0]+width/2+150-300, pos[1]+475);//bodpos[1]
    line(pos[0]+width/2+150-300, pos[1]+475, pos[0]+width/2+100-300, pos[1]+525);//leg
    line(pos[0]+width/2+100-300, pos[1]+525, pos[0]+width/2+125-300, pos[1]+600);
    line(pos[0]+width/2+150-300, pos[1]+475, pos[0]+width/2+120-300, pos[1]+515);//leg
    line(pos[0]+width/2+120-300, pos[1]+515, pos[0]+width/2+155-300, pos[1]+610);

    line(pos[0]+width/2+130-300, pos[1]+375, pos[0]+width/2+65-300, pos[1]+365);//arm
    line(pos[0]+width/2+65-300, pos[1]+365, pos[0]+width/2+60-300, pos[1]+290);
    ellipse(pos[0]+width/2+100-300, pos[1]+300, 100, 100);//head
    point(pos[0]+width/2+75-300, pos[1]+290);//epos[1]e
    line(pos[0]+width/2+80-300, pos[1]+285, pos[0]+width/2+70-300, pos[1]+288);
    line(pos[0]+width/2+65-300, pos[1]+315, pos[0]+width/2+70-300, pos[1]+315);//mouth
    line(pos[0]+width/2+130-300, pos[1]+375, pos[0]+width/2+70-300, pos[1]+355);//arm
    line(pos[0]+width/2+70-300, pos[1]+355, pos[0]+width/2+65-300, pos[1]+290);
    if(level == 4)
      popMatrix();
  }

  public void ult(int ultID) {
    if(level == 4){
      pushMatrix();
      scale(1.2f);
      translate(-130,-100);
    }
    super.ult(ultID);
    fill(rgb[0], rgb[1], rgb[2]);
    switch(this.currentUlt) {
    case 0:
      ellipse(pos[0]+width/2+80-300, pos[1]+260, 100, 100);//head
      point(pos[0]+width/2+55-300, pos[1]+250);//epos[1]e
      line(pos[0]+width/2+60-300, pos[1]+245, pos[0]+width/2+50-300, pos[1]+248);
      line(pos[0]+width/2+45-300, pos[1]+275, pos[0]+width/2+50-300, pos[1]+275);//mouth
      line(pos[0]+width/2+80-300, pos[1]+310, pos[0]+width/2+65-300, pos[1]+450);//bodpos[1]
      line(pos[0]+width/2+65-300, pos[1]+450, pos[0]+width/2+70-300, pos[1]+ 515);//leg
      line(pos[0]+width/2 + 70-300, pos[1]+ 515, pos[0]+width/2+85-300, pos[1]+ 570);
      line(pos[0]+width/2+65-300, pos[1]+450, pos[0]+width/2 + 10-300, pos[1]+ 380);//leg
      line(pos[0]+width/2+10-300, pos[1]+380, pos[0]+width/2 + 20-300, pos[1]+455);
      line(pos[0]+width/2 + 79-300, pos[1]+330, pos[0]+width/2 + 84-300, pos[1]+ 385);//arm
      line(pos[0]+width/2 + 84-300, pos[1]+385, pos[0]+width/2 + 60-300, pos[1]+360);
      line(pos[0]+width/2 + 79-300, pos[1]+330, pos[0]+width/2 + 60-300, pos[1]+380);//arm
      line(pos[0]+width/2 + 60-300, pos[1]+380, pos[0]+width/2 + 50-300, pos[1]+335);
      break;

    case 1:
      ellipse(pos[0]+width/2+80-300, pos[1]+260, 100, 100);//head
      point(pos[0]+width/2+55-300, pos[1]+250);//epos[1]e
      line(pos[0]+width/2+60-300, pos[1]+245, pos[0]+width/2+50-300, pos[1]+248);
      line(pos[0]+width/2+45-300, pos[1]+275, pos[0]+width/2+50-300, pos[1]+275);//mouth
      line(pos[0]+width/2+80-300, pos[1]+310, pos[0]+width/2+65-300, pos[1]+450);//bodpos[1]
      line(pos[0]+width/2+65-300, pos[1]+450, pos[0]+width/2+70-300, pos[1]+ 515);//leg
      line(pos[0]+width/2 + 70-300, pos[1]+ 515, pos[0]+width/2+85-300, pos[1]+ 570);
      line(pos[0]+width/2+65-300, pos[1]+450, pos[0]+width/2 - 25-300, pos[1]+ 320);//leg
      line(pos[0]+width/2 + 79-300, pos[1]+330, pos[0]+width/2 + 84-300, pos[1]+ 385);//arm
      line(pos[0]+width/2 + 84-300, pos[1]+385, pos[0]+width/2 + 60-300, pos[1]+360);
      line(pos[0]+width/2 + 79-300, pos[1]+330, pos[0]+width/2 + 60-300, pos[1]+380);//arm
      line(pos[0]+width/2 + 60-300, pos[1]+380, pos[0]+width/2 + 50-300, pos[1]+335);
      break;

    case 2:
      super.punch();
      ellipse(pos[0]+width/2-300+100, pos[1]+300, 100, 100);//head
      point(pos[0]+width/2+75-300, pos[1]+290);//epos[1]e
      line(pos[0]+width/2+80-300, pos[1]+285, pos[0]+width/2-300+70, pos[1]+288);
      line(pos[0]+width/2+65-300, pos[1]+315, pos[0]+width/2-300+70, pos[1]+315);//mouth
      line(pos[0]+width/2+125-300, pos[1]+345, pos[0]+width/2-300+150, pos[1]+475);//bodpos[1]
      line(pos[0]+width/2+150-300, pos[1]+475, pos[0]+width/2+100-300, pos[1]+525);//leg
      line(pos[0]+width/2+100-300, pos[1]+525, pos[0]+width/2+125-300, pos[1]+600);
      line(pos[0]+width/2+150-300, pos[1]+475, pos[0]+width/2+120-300, pos[1]+515);//leg
      line(pos[0]+width/2+120-300, pos[1]+515, pos[0]+width/2+155-300, pos[1]+610);
      line(pos[0]+width/2+132-300, pos[1]+375, pos[0]+width/2+155-300, pos[1]+420);//arm
      line(pos[0]+width/2+155-300, pos[1]+420, pos[0]+width/2+110-300, pos[1]+410);
      line(pos[0]+width/2+130-300, pos[1]+375, pos[0]+width/2+38-300, pos[1]+370);//arm
      line(pos[0]+width/2 + 45-300, pos[1]+375, pos[0]+width/2 - 85-300, pos[1]+325);//sword
      line(pos[0]+width/2 + 35 -300, pos[1]+365, pos[0]+width/2+ 31-300, pos[1] +376);
    default:
      break;
    }
    if(level == 4)
      popMatrix();
  }


  public void defaultScript(Person m) {
    if (this.onCooldown[0] == false &&  this.attacking == 5 && this.strength > 1
      && (((int)(random(0, level)) == 0 && mons[level] != "Big Boss man") || m.attacking != 2)) {
      this.attack = 0;
    }
    if (this.onCooldown[1] == false &&  this.attacking == 5 && this.strength > 2
      && (((int)(random(0, level)) == 0 && mons[level] != "Big Boss man") || m.attacking != 2)) {
      this.attack = 1;
    }
    if (this.onCooldown[3] == false &&  this.attacking == 5 
      && (((int)(random(0, level)) == 0 && mons[level] != "Big Boss man") || m.attacking != 2)) {
      this.attack = 3;
    }
    if (this.onCooldown[2] == false &&  this.attacking == 5
      && (((int)(random(0, level)) == 0 && mons[level] != "Big Boss man") || m.attacking != 2)) {
      this.attack = 2;
    }
  }
}

public class Ping {
  String s; //text
  int x; //x position
  int y; //y position
  int op; //opacity

  Ping(String s, int x, int y) {
    this.s = s;
    this.x = x;
    this.y = y;
    this.op = 255;
  }

  public void display() {
    textAlign(CENTER, CENTER);
    fill(220, 0, 0, op);
    textSize(18);
    text(s, x, y);
    y -= 4;
    op -= 10;
  }
}

public class Button {
  int x; // x position
  int y; //y position
  int w; // width
  String t; //text
  int ts; // text size
  int h; //height
  int clickTime = 0;
  Button() {
    
  }
  public void initialize(int x, int y, int w, int h, String t, int ts){
    this.x = x;
    this.y = y;
    this.w = w;
    this.t = t;
    this.ts = ts;
    this.h = h;
    if(clickTime == 0 && mouseX>x&&mouseY>y&&mouseY<y+h&&mouseX<x+w){
      fill(190,190,190);
      stroke(40,40,40);
    }else if(clickTime == 0){
      fill(255,255,255);
      stroke(60,60,60);
    }
    if (mousePressed && mouseX>x&&mouseY>y&&mouseY<y+h&&mouseX<x+w) {
      clickTime = 90;
      fill(255-clickTime, 255-clickTime, 255-clickTime);
      stroke(-40+clickTime,-40+clickTime,-40+clickTime);
    }else if(clickTime > 0){
      clickTime -= 30;
      fill(190-clickTime, 190-clickTime, 190-clickTime);
      stroke(40-clickTime,40-clickTime,40-clickTime);
    }
    textSize(ts);
    textAlign(CENTER, CENTER);
    strokeWeight(2);
    rect(x, y, w, 50, 5);
    fill(40, 40, 40);
    text(t, x+w/2, y+h/2-3);
  }
}

public void setup() {
  
}



public void mouseReleased() {
  if (b[0].clickTime > 0 && state == "MAIN_MENU" 
    && mouseX > width/2 - 100 && mouseY > height/2 + 50 && mouseX < width/2 + 100 && mouseY < height/2 + 100) {
    state = "HOW_TO";
    b[0].clickTime = 0;
  }
  
  if (b[12].clickTime > 0 && state == "MAIN_MENU" 
    && mouseX > width/2 - 100 && mouseY > height/2 + 105 && mouseX < width/2 + 100 && mouseY < height/2 + 155) {
    state = "CHAMP_SELECT";
    b[12].clickTime = 0;
  }
  
  if (b[1].clickTime > 0 && state == "HOW_TO" 
    && mouseX > width/2 + 250 && mouseY > height/2 + 225 && mouseX < width/2 + 450 && mouseY < height/2 + 275) {
    state = "LEVEL_CHANGE";
    b[1].clickTime = 0;
  }
  
  if (b[2].clickTime > 0 && state == "SHOP1" && winTimer <= 0 
    && mouseX > width/2 -125 && mouseY > height/2 +115 && mouseX < width/2 + 125 && mouseY < height/2 + 165) {
    winTimer = 35;
    if(level <3)
      state = "SHOP2";
    else{
      state = "MAIN_MENU";
      level = -1;
      hero.abilities = new ArrayList<String>();
    }
    b[2].clickTime = 0;
  }
  if (b[11].clickTime > 0 &&state == "LOSE" && winTimer <= 0 
    && mouseX > width/2 -125 && mouseY > height/2 +115 && mouseX < width/2 + 125 && mouseY < height/2 + 165) {
    winTimer = 35;
    state = "MAIN_MENU";
    level = -1;
    b[11].clickTime = 0;
  }

  if (state == "SHOP2") {
    if (b[3].clickTime > 0 && mouseX > width/2-400 && mouseY > 300 && mouseX < width/2 -200 
      && mouseY < 350 && !hero.abilities.contains("Boxing") && hero.abilities.size() < 3) {
      hero.abilities.add("Boxing");
      state = "LEVEL_CHANGE";
      b[3].clickTime = 0;
    }
    if (b[4].clickTime > 0 && mouseX > width/2-400 && mouseY > 355 && mouseX < width/2 -200 
      && mouseY < 405 &&!hero.abilities.contains("Kickboxing") && hero.abilities.size() < 3) {
      hero.abilities.add("Kickboxing");
      state = "LEVEL_CHANGE";
      b[4].clickTime = 0;
    }
    if (b[5].clickTime > 0 && mouseX > width/2-100 && mouseY > 300 && mouseX < width/2 +100 
      && mouseY < 350 && !hero.abilities.contains("Ninjitsu") && hero.abilities.size() < 3) {
      hero.abilities.add("Ninjitsu");
      state = "LEVEL_CHANGE";
      b[5].clickTime = 0;
    }
    if (b[6].clickTime > 0 && hero.currentUlt == 0 && mouseX > width/2-100 && mouseY > 355 
      && mouseX < width/2 +100 && mouseY < 405 && !hero.abilities.contains("Muay Thai") && hero.abilities.size() < 3) {
      hero.abilities.add("Muay Thai");
      state = "LEVEL_CHANGE";
      b[6].clickTime = 0;
    }
    if (b[7].clickTime > 0 && hero.currentUlt == 1 && mouseX > width/2-100 && mouseY > 410 
      && mouseX < width/2 +100 && mouseY < 460 && !hero.abilities.contains("Taekwondo") && hero.abilities.size() < 3) {
      hero.abilities.add("Taekwondo");
      state = "LEVEL_CHANGE";
      b[7].clickTime = 0;
    }
    if (b[8].clickTime > 0 && hero.currentUlt == 2 && mouseX > width/2-100 && mouseY > 465 
      && mouseX < width/2 +100 && mouseY < 515 &&!hero.abilities.contains("Bushido") && hero.abilities.size() < 3) {
      hero.abilities.add("Bushido");
      state = "LEVEL_CHANGE";
      b[8].clickTime = 0;
    }
    if (b[9].clickTime > 0 && !hero.abilities.contains("Muay Thai") && mouseX > width/2+200 && mouseY > 300 
      && mouseX < width/2 +400 && mouseY < 350 && hero.currentUlt != 1 && hero.currentUlt != 2 && hero.abilities.size() < 3) {
      hero.currentUlt = 1;
      hero.abilities.add("High Kick");
      state = "LEVEL_CHANGE";
      b[9].clickTime = 0;
    }
    if (b[10].clickTime > 0 && !hero.abilities.contains("Taekwondo") && !hero.abilities.contains("Muay Thai") && hero.currentUlt == 1 
      && mouseX > width/2+200 && mouseY > 355 && mouseX < width/2 +400 && mouseY < 405 
      && hero.currentUlt != 0 && hero.currentUlt != 2 && hero.abilities.size() < 3) {
      hero.currentUlt = 2;
      hero.abilities.add("Sword");
      state = "LEVEL_CHANGE";
      b[10].clickTime = 0;
    }
  }
  
  if(b[13].clickTime > 0 && mouseX > 50 && mouseY > 15 && mouseX < 250 && mouseY < 65){
    state = "MAIN_MENU";
    b[13].clickTime = 0;
  }

}

public void keyPressed() {
  //punch
  if (state == "IN_GAME" && keyCode == 81 && hero.onCooldown[0] == false &&  hero.attacking == 5 
    && keyCode != 87 && keyCode != 82 && keyCode != 69 && hero.strength > 1) {
    hero.attack = 0;
  }
  //kick
  if (state == "IN_GAME" && keyCode == 87 && hero.onCooldown[1] == false &&  hero.attacking == 5 
    && keyCode != 81 && keyCode != 82 && keyCode != 69 && hero.strength > 2) {
    hero.attack = 1;
  }
  //ult
  if (state == "IN_GAME" && keyCode == 82 && hero.onCooldown[3] == false &&  hero.attacking == 5
    && keyCode != 87 && keyCode != 81 && keyCode != 69) {
    hero.attack = 3;
  }
  //block
  if (state == "IN_GAME" && keyCode == 69 && hero.onCooldown[2] == false && hero.attacking == 5
    && keyCode != 87 && keyCode != 82 && keyCode != 81) {
    hero.attack = 2;
  }
  //cancel block
  if (hero.attacking == 2 && keyCode == 69) {
    hero.durations[2] = 0;
    hero.onCooldown[2] = true;
    hero.cooldowns[2] = 80;
    hero.attack = 4;
  }

  //combos

  //boxing
  if (hero.attacking == 2 && hero.abilities.contains("Boxing") && keyCode == 81 && !hero.onCooldown[0]) {
    hero.attack = 0;
  }
  //kickboxing
  if (hero.attacking == 0 && hero.abilities.contains("Kickboxing") && keyCode == 87 && !hero.onCooldown[1]) {
    hero.attack = 1;
  }
  //ninjitsu
  if (hero.attacking == 2 && hero.abilities.contains("Ninjitsu") && keyCode == 82 && !hero.onCooldown[3]) {
    hero.attack = 3;
  }
  //Muay Thai
  if (hero.attacking == 3 && hero.abilities.contains("Muay Thai") && keyCode == 69 && !hero.onCooldown[2]) {
    hero.attack = 2;
  }
  //Taekwondo
  if (hero.attacking == 3 && hero.abilities.contains("Taekwondo") && keyCode == 87 && !hero.onCooldown[1]) {
    hero.attack = 1;
  }

  //Bushido
  if (hero.attacking == 0 && hero.abilities.contains("Bushido") && keyCode == 82 && !hero.onCooldown[3]) {
    hero.attack = 3;
  }
}

String abilitySelection = "";

PrintWriter abilitySaves;
PrintWriter saves;
String[] lastAbilities;
String[] lastGame;
int monCurrentAttack = 0;

String state = "MAIN_MENU";

String[] ults = new String[]{"Flying knee", "Jump Kick", "Sword"};

ArrayList<Ping> pings = new ArrayList<Ping>();

String[] champions = new String[]{"White knight", "Dark knight", "Big man"};
int currentChampion = 0;

int winTimer = 35;

String[] mons = new String[]{"Green man", "Cookie man", "Polar man", "Boss man", "Big Boss man"};
int level = -1;
int levelChangeTimer = 60;

boolean endGame = false;
PrintWriter win;

Hero hero = new Hero(0, 0);
Monster mon = new Monster(300, 0);

Button[] b = new Button[]{new Button(), new Button(), new Button(), new Button(),
                          new Button(), new Button(), new Button(), new Button(),
                          new Button(), new Button(), new Button(), new Button(),
                          new Button(), new Button()};
                          
boolean won = false;
String testForWin = "";

public void draw() {
  frameRate(30);
  
  if (state == "MAIN_MENU") {
    hero = new Hero(0, 0);
    background(155, 155, 155);
    textSize(30);
    Hero h = new Hero(-400, 100);
    Monster m = new Monster(700, -100);
    m.ult(1);
    h.ult(0);
    Hero h2 = new Hero(-120, -80);
    h2.ult(2);
    Monster m2 = new Monster(400, 100);
    m2.kick();
    fill(255, 255, 255, 135);
    strokeWeight(1);
    rect(width/2-200, height/2-300, 400, 500, 10);
    fill(0, 0, 0);
    textAlign(CENTER, CENTER);
    text("Welcome to Heromon", width/2, height/2-200);
    b[0].initialize(width/2-100, height/2+50, 200, 50, "Play", 20);
    b[12].initialize(width/2-100,height/2+105,200,50,"Select Champion", 20);
    try{
      testForWin = loadStrings("won.txt")[0];
    }
    catch(NullPointerException q){
      won = false;
    }
    catch(ArrayIndexOutOfBoundsException r){
      won = true;
    }
  }
  
  if(state == "CHAMP_SELECT"){
    textAlign(CENTER, CENTER);
    background(70,70,240);
    pushMatrix();
    
    if(currentChampion !=2){
      scale(2.4f);
      translate(-100,-100);
    }else
      scale(2);
    Hero hh = new Hero(-350,-50);
    hh.rgb = new int[]{255,255,255};
    hh.idle();
    Hero hhh = new Hero(-175,-50);
    hhh.rgb = new int[]{45,45,45};
    hhh.idle();
    pushMatrix();
    scale(1.2f);
    if(currentChampion !=2)
      translate(-16.75f,-16.5f);
    Hero hhhh = new Hero(-70,-100);
    hhhh.rgb = new int[]{255,255,255};
    hhhh.idle();
    popMatrix();
    popMatrix();
    noStroke();
    fill(180,180,180);
    rect(0,0,width,80);
    textSize(60);
    fill(0);
    text("Select your Champion",width/2,30);
    fill(0,0,0,200);
    textSize(30);
    if(mouseX > width/3 || mouseY < 80)
      rect(0,80,width/3,height);
    else{
      if(mousePressed){
        currentChampion = 0;
      }
      text("The White Knight",width/3-width/6, height/2-200);
      if(currentChampion == 0){
        fill(0,0,0,200);
        noStroke();
        rect(0,height/2 , width/3, 50);
        stroke(40, 185, 40);
        fill(0, 185, 0);
        textSize(12);
        strokeWeight(4);
        line(width/2-67-350, 367, width/2-77-350, 387);
        line(width/2-77-350, 387, width/2-85-350, 379);
        text("Equipped", width/3-width/6, height/2 + 88 - 52);
        fill(0,0,0,200);
        textSize(30);
        noStroke();
      }
    }
    if(mouseX < width/3 || mouseX> 2*width/3 || mouseY < 80)
      rect(width/3,80,width/3+1,height);
    else{
      if(mousePressed){
        currentChampion = 1;
      }
      text("The Dark Knight",width/2, height/2-200);
      if(currentChampion == 1){
        fill(0,0,0,200);
        noStroke();
        rect(width/3,height/2 , width/3+1, 50);
        stroke(40, 185, 40);
        fill(0, 185, 0);
        textSize(12);
        strokeWeight(4);
        line(width/3+width/2-67-350, 367, width/3+width/2-77-350, 387);
        line(width/3+width/2-77-350, 387, width/3+width/2-85-350, 379);
        text("Equipped", width/2, height/2 + 88 - 52);
        fill(0,0,0,200);
        textSize(30);
        noStroke();
      }
    }
    if(mouseX < 2* width/3 || mouseY < 80)
      rect(2*width/3,80,width/3,height);
    else{
      if(mousePressed && won){
        currentChampion = 2;
      }
      text("The Big Man",2* width/3+width/6, height/2-250);
      if(won == false){
        fill(0,0,0,200);
        noStroke();
        rect(2*width/3,height/2 , width/3+2, 50);
        textSize(12);
        stroke(235, 0, 0);
        strokeWeight(2);
        fill(235, 0, 0);
        rect(width/2 -85 + 500, height/2 + 68 -110 + 55, 16, 14, 2);
        text("champion locked", width/2 - 95 + 520, height/2 - 110 + 88 + 55);
        noFill();
        ellipse(width/2 -77 + 500, height/2 + 68-110 + 55, 12, 16);
      }
      if(currentChampion == 2){
        fill(0,0,0,200);
        noStroke();
        rect(2*width/3,height/2 , width/3+1, 50);
        stroke(40, 185, 40);
        fill(0, 185, 0);
        textSize(12);
        strokeWeight(4);
        line(2*width/3+width/2-67-350, 367, 2 * width/3+width/2-77-350, 387);
        line(2*width/3+width/2-77-350, 387, 2* width/3+width/2-85-350, 379);
        text("Equipped", 2*width/3 + width/6, height/2 + 88 - 52);
        fill(0,0,0,200);
        textSize(30);
        noStroke();
      }
    }
    b[13].initialize(50,15,200,50,"Back",20);
  }
  

  if (state == "HOW_TO") {
    background(90, 90, 240);
    fill(255, 255, 255, 75);
    stroke(0, 0, 0);
    strokeWeight(2);
    rect(10, 10, width-20, height-20, 5);
    textSize(50);
    fill(0, 0, 0, 200);
    textAlign(CENTER, CENTER);
    text("How to play (please read)", width/2, 100);
    textSize(20);
    textAlign(LEFT, CENTER);
    text("Press q to punch\nPress w to kick\nPress e to toggle block\nPress r to use ultimate attack\n\n" + 
      "Punching and kicking will cost strength\nYou can recieve strength by blocking an enemy's ultimate attack\n" +
      "You cannot punch or kick if you do not have enough strength\n\n"+
      "Health and strength are indicated at the top of the screen\nHealth is red\nStrength is blue\n\n" +
      "The character on the left is your champion\nHave fun!", width/2-350, height/2 + 50);

    b[1].initialize(width/2 + 250, height/2 + 225, 200, 50, "Begin", 20);
  }



  if (state == "SHOP1") {
    fill(0,0,60,4);
    stroke(0,0,0,6);
    strokeWeight(1);
    rect(width/2-200,height/2-210,400,420,10);
    fill(210, 210, 210, 116);
    textSize(70);
    text("Level "+ (level+1) +" Win", width/2+2, height/2-160);
    if (winTimer > -10)
      winTimer--;
    if (winTimer <= 0) {
      fill(210, 210, 210);
      text("Level "+ (level+1) +" Win", width/2+2, height/2-160);
      b[2].initialize(width/2-125, height/2 +115, 250, 50, "Continue", 20);
      mon = new Monster(300, 0);
    }
  }

  if (state == "SHOP2") {
    mon = new Monster(300, 0);
    background(40, 40, 240, 50);
    fill(250, 250, 250, 210);
    strokeWeight(1);
    stroke(0, 0, 0);
    rect(30, 30, width-60, height-60, 5);
    fill(60, 60, 60);
    textSize(50);
    textAlign(CENTER, CENTER);
    text("Chose skill to continue", width/2, 100);
    textSize(20);
    fill(60, 60, 60);
    text("Basic combos", width/2-300, 250);
    b[3].initialize(width/2-400, 300, 200, 50, "Boxing", 20);
    b[4].initialize(width/2-400, 355, 200, 50, "Kickboxing", 20);
    fill(60, 60, 60);
    text("Ultimate combos", width/2, 250);
    b[5].initialize(width/2-100, 300, 200, 50, "Ninjitsu", 20);
    b[6].initialize(width/2-100, 355, 200, 50, "Muay Thai", 20);
    b[7].initialize(width/2-100, 410, 200, 50, "Taekwondo", 20);
    b[8].initialize(width/2-100, 465, 200, 50, "Bushido", 20);
    fill(60, 60, 60);
    text("Ultimate Upgrades", width/2 + 300, 250);
    b[9].initialize(width/2+200, 300, 200, 50, "High Kick", 20);
    b[10].initialize(width/2+200, 355, 200, 50, "Sword", 20);
    textAlign(LEFT, CENTER);
    textSize(12);
    stroke(235, 0, 0);
    strokeWeight(2);
    if (hero.currentUlt == 0 || hero.abilities.contains("Muay Thai") || hero.abilities.contains("Taekwondo")) {
      //sword lock
      fill(235, 0, 0);
      rect(width/2 -85 + 300, height/2 + 68 -110 + 55, 16, 14, 2);
      text("locked", width/2 - 95 + 300, height/2 - 110 + 88 + 55);
      noFill();
      ellipse(width/2 -77 + 300, height/2 + 68-110 + 55, 12, 16);
    }
    if (hero.abilities.contains("Muay Thai")) {
      //high kick lock
      fill(235, 0, 0);
      rect(width/2 -85 + 300, height/2 + 68 -110, 16, 14, 2);
      text("locked", width/2 - 95 + 300, height/2 - 110 + 88);
      noFill();
      ellipse(width/2 -77 + 300, height/2 + 68-110, 12, 16);
    }
    if (hero.currentUlt == 0 || hero.currentUlt == 2) {
      //taekwondo lock
      fill(235, 0, 0);
      rect(width/2 - 85, height/2 + 68, 16, 14, 2);
      text("locked", width/2-95, height/2 + 88);
      noFill();
      ellipse(width/2 -77, height/2 + 68, 12, 16);
    }
    if (hero.currentUlt == 1 || hero.currentUlt == 0) {
      //bushido lock
      fill(235, 0, 0);
      rect(width/2 - 85, height/2 + 68 + 55, 16, 14, 2);
      text("locked", width/2-95, height/2 + 88 + 55);
      noFill();
      ellipse(width/2 - 77, height/2 + 68 + 55, 12, 16);
    }
    if (hero.currentUlt == 2 || hero.currentUlt == 1) {
      //muay thai lock
      fill(235, 0, 0);
      rect(width/2 - 85, height/2 + 68-55, 16, 14, 2);
      text("locked", width/2-95, height/2 + 88-55);
      noFill();
      ellipse(width/2 -77, height/2 + 68-55, 12, 16);
    }

    stroke(40, 185, 40);
    fill(0, 185, 0);
    strokeWeight(4);
    if (hero.abilities.contains("Ninjitsu")) {
      line(width/2-67, 310, width/2-77, 330);
      line(width/2-77, 330, width/2-85, 322);
      text("Owned", width/2-95, height/2 + 88 - 110);
    }
    if (hero.abilities.contains("Muay Thai")) {
      line(width/2-67, 310 + 55, width/2-77, 330 + 55);
      line(width/2-77, 330 + 55, width/2-85, 322 + 55);
      text("Owned", width/2-95, height/2 + 88 - 55);
    }
    if (hero.abilities.contains("Boxing")) {
      line(width/2-67-300, 310, width/2-77-300, 330);
      line(width/2-77-300, 330, width/2-85-300, 322);
      text("Owned", width/2-95-300, height/2 + 88 - 110);
    }
    if (hero.abilities.contains("Kickboxing")) {
      line(width/2-67-300, 310 + 55, width/2-77-300, 330 + 55);
      line(width/2-77-300, 330 + 55, width/2-85-300, 322 + 55);
      text("Owned", width/2-95-300, height/2 + 88 - 55);
    }
    if (hero.abilities.contains("High Kick") || hero.abilities.contains("Sword")) {
      line(width/2-67+300, 310, width/2-77+300, 330);
      line(width/2-77+300, 330, width/2-85+300, 322);
      text("Owned", width/2-95+300, height/2 + 88 - 110);
    }
    if (hero.abilities.contains("Sword")) {
      line(width/2-67+300, 310 + 55, width/2-77+300, 330 + 55);
      line(width/2-77+300, 330 + 55, width/2-85+300, 322 + 55);
      text("Owned", width/2-95+300, height/2 + 88 - 55);
    }
    if (hero.abilities.contains("Taekwondo")) {
      line(width/2-67, 310 + 110, width/2-77, 330 + 110);
      line(width/2-77, 330 + 110, width/2-85, 322 + 110);
      text("Owned", width/2-95, height/2 + 88);
    }
    if (hero.abilities.contains("Bushido")) {
      line(width/2-67, 310 + 165, width/2-77, 330 + 165);
      line(width/2-77, 330 + 165, width/2-85, 322 + 165);
      text("Owned", width/2-95, height/2 + 88 + 55);
    }

    strokeWeight(2);
    textSize(14);
    stroke(0, 0, 0);
    if (mouseX > width/2-400 && mouseY > 300 && mouseX < width/2 -200 && mouseY < 350) {
      fill(0, 0, 0, 190);
      rect(mouseX, mouseY, 300, -300, 5);
      fill(255, 255, 255);
      text("Boxing\n\nUnique Passive:\nWhen blocking, you can stop the block \n"
        +"by punching to critically strike* the \nopponent.\n\n"
        +"*Critical strike deals 2 times \ndamage when not blocked, and 0.5 \ntimes damage when blocked\n\n" 
        + "Damage dealt if blocked: "+hero.punchDmg/2+"\nDamage dealt if not blocked: "+hero.punchDmg * 2, mouseX + 14, mouseY - 150);
    }
    if (mouseX > width/2-400 && mouseY > 355 && mouseX < width/2 -200 && mouseY < 405) {
      fill(0, 0, 0, 190);
      rect(mouseX, mouseY, 300, -300, 5);
      fill(255, 255, 255);
      text("Kickboxing\n\nUnique Passive:\nIf you punch and immediately follow\n"
        +"up with a kick, that kick will be\nempowered*.\n\n*Empowered attacks do 2 times damage"
        +"\nif they are not blocked, and refund \nstrength if blocked\n\n" 
        + "Strength lost if blocked: 0\nDamage dealt if not blocked: "+2 * hero.kickDmg, mouseX + 14, mouseY - 150);
    }
    if (mouseX > width/2-100 && mouseY > 300 && mouseX < width/2 +100 && mouseY < 350) {
      fill(0, 0, 0, 190);
      rect(mouseX, mouseY, 300, -300, 5);
      fill(255, 255, 255);
      text("Ninjitsu\n\nUnique Passive:\nPreforming your ultimate attack while \n"
        +"blocking will cause your ultimate to \ncritically strike*\n"
        +"\n*Critical strike deals 2 times damage \nwhen not blocked, and 0.5 times \ndamage when blocked\n\n" 
        + "Damage dealt if blocked: "+(int)(hero.ultDmg)/2+"\nDamage dealt if not blocked: "+(int)(hero.ultDmg) * 2, mouseX + 14, mouseY - 150);
    }
    if (mouseX > width/2-100 && mouseY > 355 && mouseX < width/2 +100 && mouseY < 405) {
      fill(0, 0, 0, 190);
      rect(mouseX, mouseY, 300, -236, 5);
      fill(255, 255, 255);
      text("Muay Thai\n\nUnique Passive:\n(Requires Flying Knee Ultimate) \nIf you use flying knee,"
        +" and immediately \nblock an attack, your block will become \nempowered*\n"
        +"\n*Empowered blocks give you strength \nbased on enemy attacks during block" 
        + 5, mouseX + 14, mouseY - 118);
    }
    if (mouseX > width/2-100 && mouseY > 410 && mouseX < width/2 +100 && mouseY < 460) {
      fill(0, 0, 0, 190);
      rect(mouseX, mouseY, 300, -344, 5);
      fill(255, 255, 255);
      text("Taekwondo\n\nUnique Passive:\n(Requires High Kick Ultimate)\n"
        +"After preforming your high kick, \nimmediately following up with a normal \nkick will"
        +"cause your kick to critically \nstrike*\n\n*Critical strike deals 2 times damage \n"
        +"when not blocked, and 0.5 times \ndamage when blocked\n\n" 
        + "Damage dealt if blocked: "+hero.kickDmg/2+"\nDamage dealt if not blocked: "+hero.kickDmg * 2, mouseX + 14, mouseY - 172);
    }
    if (mouseX > width/2-100 && mouseY > 465 && mouseX < width/2 +100 && mouseY < 515) {
      fill(0, 0, 0, 190);
      rect(mouseX, mouseY, 300, -236, 5);
      fill(255, 255, 255);
      text("Bushido\n\nUnique Passive:\n(Requires Sword Ultimate)\n"
        +"After preforming a punch, immediately \nfollow up with a sword attack to gain "
        +"\n30% lifesteal* on that attack\n\n*Lifesteal heals you for a percentage \n"
        +"of damage dealt", mouseX + 14, mouseY - 118);
    }
    if (mouseX > width/2+200 && mouseY > 300 && mouseX < width/2 +400 && mouseY < 350) {
      fill(0, 0, 0, 190);
      rect(mouseX, mouseY, 300, -274, 5);
      fill(255, 255, 255);
      text("High Kick\n\nBase Stats Increase:\n+2 ult dmg\n\n"
        +"Unique Passive:\nUnlocks Taekwondo skill for purchase \nnext round\n\n"
        +"Unique Passive:\nUnlocks Sword upgrade for purchase \nnext round", mouseX + 14, mouseY - 137);
    }
    if (mouseX > width/2+200 && mouseY > 355 && mouseX < width/2 +400 && mouseY < 405) {
      fill(0, 0, 0, 190);
      rect(mouseX, mouseY, 300, -208, 5);
      fill(255, 255, 255);
      text("Sword\n\nUnique Passive:\nDeals 20% of opponent's missing health \nas damage\n\n"
        +"Unique Passive:\nUnlocks Bushido skill for purchase \nnext round", mouseX + 14, mouseY - 104);
    }

    //create hero combo indicators
    textAlign(CENTER, CENTER);
    fill(40, 40, 40);
    textSize(12);
    text("Owned:", 90, height-102);
    text("(none)", 90, height-82);
    stroke(0, 0, 0);
    strokeWeight(1);
    for (int i = 0; i < hero.abilities.size(); i ++) {
      fill(155, 155, 155);
      rect(50 + i * 90, height-90, 80, 20, 4);
      fill(0, 0, 0);
      text(hero.abilities.get(i), 90 + i * 90, height-82);
    }
  }


  if (state == "LEVEL_CHANGE") {
    endGame = false;

    hero.pos[0] = hero.originalX;

    hero.health = 100;
    hero.strength = 50;

    monCurrentAttack = 0;

    hero.cooldowns = new int[]{0, 80, 0, 300};
    mon.cooldowns = new int[]{0, 80, 0, 300};

    hero.onCooldown = new boolean[]{false, true, false, true};

    hero.crit = false;
    hero.empowered = false;
    hero.lifesteal = false;

    mon.crit = false;
    mon.empowered = false;
    mon.lifesteal = false;

    hero.attack = 4;//4 is idle
    hero.attacking = 5;//5 is nothing

    mon.attack = 4;//4 is idle
    mon.attacking = 5;//5 is nothing

    fill(125, 125, 125, 80);
    rect(-10, -10, width + 40, height+40);
    textAlign(CENTER, CENTER);
    fill(255, 255, 255);
    
    levelChangeTimer-=1;
    
    textSize(300);
    
    if (levelChangeTimer > 1) {
      text(levelChangeTimer/20 + 1, width/2, height/2-100);
    } else if (levelChangeTimer < 0) {
      text("FIGHT!", random(width/2-4, width/2 +4), random(height/2-100-2, height/2-100+2));
    }
    
    if (levelChangeTimer < -20) {
      state = "IN_GAME";
      levelChangeTimer = 60;
      
      level++;
      
      mon = new Monster(300, 0);
      
      lastAbilities = loadStrings("abilities"+(level)+".txt");
      lastGame = loadStrings("lastGame"+(level)+".txt");

      try {
        for (int i = 0; i < lastAbilities.length; i++) {
          mon.abilities.add(lastAbilities[i]);
        }
      }
      catch(NullPointerException e) {
        //do nothing
      }
      
      abilitySaves = createWriter("abilities"+(level)+".txt");
      saves = createWriter("lastGame"+(level)+".txt");

      for (int i = 0; i < hero.abilities.size(); i++) {
        abilitySaves.println(hero.abilities.get(i));
      }
      
      try {
        abilitySaves.flush();
        abilitySaves.close();
      }
      catch(NullPointerException f) {
        
      }
      
      //this should make it use the saves from the last level 3, and then go to level 4
      if(champions[currentChampion] == "Big man" && level == 3)
        level++;
    }
  }



  //in game state

  if (state == "IN_GAME") {
    textAlign (CENTER, CENTER);
    background(90, 90, 240);



    if (level == 0) {
      hero.currentUlt = 0;
      mon.currentUlt = 0;
    }

    //create health & strength bars
    stroke(0, 0, 0);
    strokeWeight(2);
    noFill();
    rect(-3, 0, width+5, 79);
    for (int i = 0; i < 80; i+=5) {
      strokeWeight(5);
      stroke(255-i/2-90, 255-i/3-90, 255-i-20);
      line(0, i, width, i);
    }

    textAlign(CENTER, CENTER);
    stroke(0, 0, 0);
    strokeWeight(1);

    //create hero combo indicators
    for (int i = 0; i < hero.abilities.size(); i ++) {
      fill(155, 155, 155);
      rect(10 + i * 90, 90, 80, 20, 4);
      textSize(12);
      fill(0, 0, 0);
      text(hero.abilities.get(i), 50 + i * 90, 99);
    }

    //create monster combo indicators
    for (int i = 0; i < mon.abilities.size(); i ++) {
      fill(155, 155, 155);
      rect(width- (10 + i * 90)-1, 90, -80, 20, 4);
      textSize(12);
      fill(0, 0, 0);
      text(mon.abilities.get(i), width- (50 + i * 90)-1, 99);
    }

    fill(0, 0, 0, 60);
    strokeWeight(2);
    stroke(0, 0, 0);
    rect(5, 5, width/2-100, 40, 5);
    fill(155, 0, 0);
    strokeWeight(1);
    if (hero.health > 0)
      rect(6, 6, (int)((hero.health * 0.01f) * (width/2-100-2)), 38, 5);

    fill(0, 0, 0, 60);
    strokeWeight(2);
    stroke(0, 0, 0);
    rect(width/2+95, 5, width/2-100, 40, 5);
    strokeWeight(1);
    fill(155, 0, 0);
    if (mon.health > 0)
      rect(width - 6, 6, (int)(-1 * mon.health * 0.01f * (width/2-100-2)), 38, 5);

    //create strength bars
    fill(0, 0, 0, 60);
    strokeWeight(1);
    stroke(0, 0, 0);
    rect(5, 50, width/2-200, 21, 5);
    fill(60, 195, 250);
    strokeWeight(1);
    rect(6, 50, (int)((hero.strength * 0.01f) * (width/2-200-2)), 20, 5);

    fill(0, 0, 0, 60);
    strokeWeight(1);
    stroke(0, 0, 0);
    rect(width/2+195, 50, width/2-200, 21, 5);
    strokeWeight(1);
    fill(60, 195, 250);
    rect(width - 6, 50, (int)(-1 * mon.strength * 0.01f * (width/2-200-2)), 20, 5);
    textSize(20);
    fill(255, 255, 255, 210);
    textAlign(RIGHT, CENTER);
    text("Hero: " + champions[currentChampion] + " (you)", width/2 - 110, 22);
    textAlign(LEFT, CENTER);
    text("Monster: " + mons[level], width/2 + 110, 22);
    textAlign(CENTER, CENTER);

    //create ability indicators
    strokeWeight(4);
    stroke(0, 0, 0);
    if (!hero.onCooldown[0] && hero.strength > 1) {
      fill(20, 200, 40);
      rect(width/2 - 225, 640, 50, 50, 10);
      fill(0, 0, 0);
      textSize(40);
      text("Q", width/2 - 200, 660);
      textSize(14);
      text("Punch", width/2-200, 700);
    } else if (hero.onCooldown[0]) {
      fill(255, 150, 125);
      rect(width/2 - 225, 640, 50, 50, 10);
      fill(250, 250, 250);
      textSize(38);
      text("" + (hero.cooldowns[0]/10 + 1), width/2 - 200, 660);
      textSize(14);
      fill(0, 0, 0);
      text("Punch Cooldown", width/2-200, 700);
    } else if (hero.strength <= 1) {
      fill(110, 250, 110);
      rect(width/2 - 225, 640, 50, 50, 10);
      fill(0, 0, 0);
      textSize(60);
      fill(250, 250, 250);
      text("X", width/2-199, 658);
      textSize(14);
      fill(0, 0, 0);
      text("Strength low!", width/2-200, 700);
    }

    if (!hero.onCooldown[1] && hero.strength > 2) {
      fill(20, 200, 40);
      rect(width/2 - 100+6, 640, 50, 50, 10);
      fill(0, 0, 0);
      textSize(40);
      text("W", width/2-75+6, 660+2);
      textSize(14);
      text("Kick", width/2-75+6, 700);
    } else if (hero.onCooldown[1]) {  
      fill(255, 150, 125);
      rect(width/2 - 100+6, 640, 50, 50, 10);
      fill(0, 0, 0);
      textSize(38);
      fill(250, 250, 250);
      text("" + (hero.cooldowns[1]/10 + 1), width/2-75+6, 660);
      textSize(14);
      fill(0, 0, 0);
      text("Kick cooldown", width/2-75+6, 700);
    } else if (hero.strength <= 2) {
      fill(110, 250, 110);
      rect(width/2 - 100+6, 640, 50, 50, 10);
      fill(0, 0, 0);
      textSize(60);
      fill(250, 250, 250);
      text("X", width/2-74+6, 658);
      textSize(14);
      fill(0, 0, 0);
      text("Strength low!", width/2-75+6, 700);
      endGame = true;
    }

    if (!hero.onCooldown[2]) {
      fill(20, 200, 40);
      rect(width/2 + 25+25-6.5f, 640, 50, 50, 10);
      fill(0, 0, 0);
      textSize(40);
      text("E", width/2+50+25-6.5f-2, 660+1);
      textSize(14);
      text("Block", width/2+50+25-6.5f, 700);
    } else {  
      fill(255, 150, 125);
      rect(width/2 + 25+25-6.5f, 640, 50, 50, 10);
      fill(0, 0, 0);
      textSize(38);
      fill(250, 250, 250);
      text("" + (hero.cooldowns[2]/10 + 1), width/2+50+25-6.5f, 660);
      textSize(14);
      fill(0, 0, 0);
      text("Block cooldown", width/2+50+25-6.5f, 700);
    }

    if (!hero.onCooldown[3]) {
      fill(20, 200, 40);
      rect(width/2 + 175, 640, 50, 50, 10);
      fill(0, 0, 0);
      textSize(40);
      text("R", width/2 + 200, 660);
      textSize(14);
      text(ults[hero.currentUlt], width/2 + 200, 700);
    } else {  
      fill(255, 150, 125);
      rect(width/2 + 175, 640, 50, 50, 10);
      fill(0, 0, 0);
      textSize(38);
      fill(250, 250, 250);
      text("" + (hero.cooldowns[3]/10 + 1), width/2 +200, 660);
      textSize(14);
      fill(0, 0, 0);
      text("Ult Cooldown", width/2+200, 700);
    }

    if (hero.attacking == 2) {
      fill(20, 120, 165, 110);
      noStroke();
      rect(width/2 + 27+19, 688, 46, -(hero.durations[2])-4, 10);
    }

    for (int i = 0; i < pings.size(); i++) {
      pings.get(i).display();
      if (pings.get(i).op < 0) {
        pings.remove(i);
      }
    }

    mon.attackMechanics(hero);
    hero.attackMechanics(mon);

    try {
      if (mon.strength > 1) {
        if (lastGame[monCurrentAttack].equals("q")) {
          mon.attack = 0;
        } else if (lastGame[monCurrentAttack].equals("w")) {
          mon.attack = 1;
        } else if (lastGame[monCurrentAttack].equals("e")) {
          mon.attack = 2;
        } else if (lastGame[monCurrentAttack].equals("r")) {
          mon.attack = 3;
        } else if (lastGame[monCurrentAttack].equals("i")) {
          mon.attack = 4;
        }
      } else {
        mon.defaultScript(hero);
      }
      monCurrentAttack++;
    }
    catch(NullPointerException e) {
      mon.defaultScript(hero);
    }
    catch(ArrayIndexOutOfBoundsException f) {
      mon.defaultScript(hero);
    }

    if (hero.health <=0) {
      state = "LOSE";
      saves.flush();
      saves.close();
    }

    if (mon.health <= 0) {
      state = "SHOP1";
      saves.flush();
      saves.close();
      if(level == 3){
        won = true;
        win = createWriter("won.txt");
        win.flush();
        win.close();
      }
    }
  }
  
  if(state == "LOSE"){
    fill(0,0,60,4);
    stroke(0,0,0,6);
    strokeWeight(1);
    rect(width/2-200,height/2-210,400,420,10);
    fill(190, 0, 0, 16);
    textSize(90);
    text("DEFEAT", width/2, height/2-160);
    if (winTimer > -10)
      winTimer--;
    if (winTimer <= 0) {
      b[11].initialize(width/2-125, height/2 +115, 250, 50, "Continue", 20);
      mon = new Monster(300, 0);
    }
  }
}
  public void settings() {  size(1280, 720); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Heromon" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
