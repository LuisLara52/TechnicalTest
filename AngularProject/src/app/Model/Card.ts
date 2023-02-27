export class Card {
    pan: string;
    titular:String;
    cedula:String;
    telefono:String;
    estado:String;

    constructor(pan:string, titular:string, cedula:string, telefono:string, estado:string){
        this.pan=pan;
        this.titular=titular;
        this.cedula=cedula;
        this.telefono=telefono;
        this.estado=estado;
    }
}