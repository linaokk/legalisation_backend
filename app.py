#!/usr/bin/python3


from flask import Flask, jsonify, request
from deepface import DeepFace


def has_save_face(img1, img2):
    try:
        result = DeepFace.verify(img1_path=img1, img2_path=img2)
        return result['verified']
    except:
        return False


app = Flask(__name__)


@app.route("/api/face_recognition/check", methods=['POST'])
def face_check():
    face1 = request.json["face1"]
    face2 = request.json["face2"]

    result = has_save_face(face1, face2)

    jsonResponse = {"result": True if result == True else False}
    return jsonify(jsonResponse)


app.run(port=9000)
