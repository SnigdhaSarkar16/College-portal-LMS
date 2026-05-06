import React, { useState } from "react";

function FacultyPanel() {

  const [studentId, setStudentId] = useState("");
  const [courseId, setCourseId] = useState("");

  const [status, setStatus] = useState("");

  const [theory, setTheory] = useState("");
  const [practical, setPractical] = useState("");

  const token = localStorage.getItem("token");

  const markAttendance = async () => {

    const response = await fetch(
      `http://localhost:8080/api/faculty/attendance?studentId=${studentId}&courseId=${courseId}&status=${status}&date=2026-05-06`,
      {
        method: "POST",
        headers: {
          Authorization: "Bearer " + token
        }
      }
    );

    const data = await response.json();

    console.log(data);

    alert("Attendance Added");
  };

  const addMarks = async () => {

    const response = await fetch(
      `http://localhost:8080/api/faculty/marks?studentId=${studentId}&courseId=${courseId}&theory=${theory}&practical=${practical}`,
      {
        method: "POST",
        headers: {
          Authorization: "Bearer " + token
        }
      }
    );

    const data = await response.json();

    console.log(data);

    alert("Marks Added");
  };

  return (

    <div style={{ padding: 30 }}>

      <h1>Faculty Dashboard</h1>

      <input
        placeholder="Student ID"
        onChange={(e) => setStudentId(e.target.value)}
      />

      <br /><br />

      <input
        placeholder="Course ID"
        onChange={(e) => setCourseId(e.target.value)}
      />

      <br /><br />

      <input
        placeholder="Attendance Status"
        onChange={(e) => setStatus(e.target.value)}
      />

      <br /><br />

      <button onClick={markAttendance}>
        Add Attendance
      </button>

      <hr />

      <input
        placeholder="Theory Marks"
        onChange={(e) => setTheory(e.target.value)}
      />

      <br /><br />

      <input
        placeholder="Practical Marks"
        onChange={(e) => setPractical(e.target.value)}
      />

      <br /><br />

      <button onClick={addMarks}>
        Add Marks
      </button>

    </div>
  );
}

export default FacultyPanel;